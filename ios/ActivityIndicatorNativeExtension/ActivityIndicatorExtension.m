//
//  AdobeeAirExtensionIOS.m
//  AdobeeAirExtensionIOS
//
//  Created by Oren Baranes on 12/9/13.
//  Copyright (c) 2013 Oren Baranes. All rights reserved.
//

#import "FlashRuntimeExtensions.h"
#import "ActivityIndicatorExtension.h"
#import "UIKit/UIActivityIndicatorView.h"
#import "UIKit/UIApplication.h"
#import "UIKit/UIWindow.h"


//UIActivityIndicatorView *spinner;

NSMutableDictionary *spinners = nil;

int32_t lastSpinnerID;

UIColor* UIColorFromRGB(NSInteger rgbValue) {
    return [UIColor colorWithRed:((float)((rgbValue & 0xFF0000) >> 16))/255.0
                           green:((float)((rgbValue & 0xFF00) >> 8))/255.0
                            blue:((float)(rgbValue & 0xFF))/255.0
                           alpha:1.0];
}

FREObject show(FREContext ctx, void* funcData, uint32_t argc, FREObject argv[])
{
    int32_t style;
    FREGetObjectAsInt32(argv[2], &style);
    
    //uint32_t backgroundColor;
    //FREGetObjectAsUint32(argv[3], &backgroundColor);
    
    uint32_t spinnerColor;
    FREGetObjectAsUint32(argv[3], &spinnerColor);
    
    int32_t centerX;
    FREGetObjectAsInt32(argv[0], &centerX);
    
    int32_t centerY;
    FREGetObjectAsInt32(argv[1], &centerY);
    
    NSLog(@"Show call with params %d, %d, %d, %d", style, spinnerColor, centerX, centerY);
    
    UIActivityIndicatorView *spinner = [[UIActivityIndicatorView alloc]initWithActivityIndicatorStyle:style];
    spinner.hidesWhenStopped = YES;
    if ([spinner respondsToSelector:@selector(setColor:)]) {
        [spinner setColor: UIColorFromRGB(spinnerColor)];
    }

    CGRect frame = spinner.frame;
    NSLog(@"Frame size %f, %f, %f, %f", frame.origin.x, frame.origin.y, frame.size.width, frame.size.height);
    frame.origin.x = centerX - frame.size.width / 2;
    frame.origin.y = centerY - frame.size.height / 2;
    spinner.frame = frame;
    NSLog(@"Frame size %f, %f, %f, %f", frame.origin.x, frame.origin.y, frame.size.width, frame.size.height);
    NSLog(@"Window size %f, %f", [UIApplication sharedApplication].keyWindow.frame.size.width, [UIApplication sharedApplication].keyWindow.frame.size.height);

    lastSpinnerID++;
    NSString *key = [NSString stringWithFormat:@"%d", lastSpinnerID];
    [spinners setObject:spinner forKey:key];
    
    NSLog(@"Spinner key: %@", key);
    
    [[UIApplication sharedApplication].keyWindow addSubview:spinner];
    [spinner startAnimating];
    
    FREObject res = nil;
    FRENewObjectFromInt32(lastSpinnerID, &res);
    
    return res;
}

FREObject hide(FREContext ctx, void* funcData, uint32_t argc, FREObject argv[]) {
    int32_t spinnerID;
    FREGetObjectAsInt32(argv[0], &spinnerID);
    NSString *key = [NSString stringWithFormat:@"%d", spinnerID];
    NSLog(@"Hide spinner with id: %@", key);
    UIActivityIndicatorView *spinner = spinners[key];
    if(spinners != nil) {
        [spinner stopAnimating];
        [spinners removeObjectForKey:key];
        NSLog(@"Spinner is hidden");
    }
    return NULL;
}

FREObject hideAll(FREContext ctx, void* funcData, uint32_t argc, FREObject argv[]) {
    NSLog(@"Hide all spinners");
    UIActivityIndicatorView *spinner = nil;
    for (id key in spinners) {
        NSLog(@"There are %@ %@'s in stock", spinners[key], key);
        spinner = (UIActivityIndicatorView* )spinners[key];
        [spinner stopAnimating];
        [spinners removeObjectForKey:key];
    }
    return NULL;
}

void AFExtContextInitializer(void* extData, const uint8_t* ctxType, FREContext ctx, uint32_t* numFunctionsToTest, const FRENamedFunction** functionsToSet)
{
    
    *numFunctionsToTest = 3;
    FRENamedFunction* func = (FRENamedFunction*)malloc(sizeof(FRENamedFunction) * *numFunctionsToTest);
    
    func[0].name = (const uint8_t*)"show";
    func[0].functionData = NULL;
    func[0].function = &show;
    
    func[1].name = (const uint8_t*)"hide";
    func[1].functionData = NULL;
    func[1].function = &hide;

    func[2].name = (const uint8_t*)"hideAll";
    func[2].functionData = NULL;
    func[2].function = &hideAll;
    *functionsToSet = func;
    
    spinners = [[NSMutableDictionary alloc] init];
    lastSpinnerID = 0;
    
    NSLog(@"Init extension");
    
}


void AFExtensionInitializer(void** extDataToSet, FREContextInitializer* ctxInitializerToSet, FREContextFinalizer* ctxFinalizerToSet)
{
    *extDataToSet = NULL;
    *ctxInitializerToSet = &AFExtContextInitializer;
}

void AFExtensionFinalizer(FREContext ctx)
{
    return;
}















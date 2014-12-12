package com.trembit.activityindicator {
import flash.events.EventDispatcher;
import flash.events.IEventDispatcher;
import flash.external.ExtensionContext;

public class ActivityIndicator extends EventDispatcher {

	private static var context:ExtensionContext;

	public function ActivityIndicator(target:IEventDispatcher = null) {
		if (!context) {
			try {
				context = ExtensionContext.createExtensionContext("com.trembit.activityindicator", null);
			} catch (e:ArgumentError) {
			}
		}
		super(target);
	}

	public function show(centerX:int=0, centerY:int=0, style:int = 1, color:uint = -1):int {
		return context.call("show", centerX, centerY, style, color) as int;
	}

	public function hide(id:int):void {
		context.call("hide", id);
	}

	public function hideAll():void {
		context.call("hideAll");
	}

}
}

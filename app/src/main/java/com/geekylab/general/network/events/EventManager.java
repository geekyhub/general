package com.geekylab.general.network.events;

import de.greenrobot.event.EventBus;

public class EventManager {
    private static EventManager instance = null;
    private EventBus mEventBus;

    public static EventManager getInstance() {
        if(instance==null){
            instance = new EventManager();
            instance.mEventBus = new EventBus();
        }
        return instance;
    }

    private EventManager() {

    }

    public static EventBus getBus(){
        return getInstance().mEventBus;
    }
}

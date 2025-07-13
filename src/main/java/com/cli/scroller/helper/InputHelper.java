package com.cli.scroller.helper;

import com.cli.scroller.models.actions.Action;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.cli.scroller.models.actions.Action.*;

public class InputHelper {

    public static final Map<Integer, Action> MOVEMENT_MAP;
    static {
        Map<Integer, Action> map = new HashMap<>();
        map.put(119, JUMP); // w
        map.put(100, MOVE_RIGHT); // d
        map.put(97, MOVE_LEFT); // a
        map.put(115, DROP); // s
        MOVEMENT_MAP = Collections.unmodifiableMap(map);
    }

    public static final Map<Integer, Action> INTERACT_MAP;
    static {
        Map<Integer, Action> map = new HashMap<>();
        map.put(32, CHANGE_EQUIPPED); // space
        map.put(112, USE_EQUIPPED); // p
        map.put(120, DUMP); // p
        INTERACT_MAP = Collections.unmodifiableMap(map);
    }


}

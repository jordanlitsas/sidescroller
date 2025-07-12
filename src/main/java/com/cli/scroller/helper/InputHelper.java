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
        map.put(119, JUMP);
        map.put(100, MOVE_RIGHT);
        map.put(97, MOVE_LEFT);
        map.put(115, DROP);
        MOVEMENT_MAP = Collections.unmodifiableMap(map);
    }


}

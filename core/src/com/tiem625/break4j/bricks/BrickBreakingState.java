package com.tiem625.break4j.bricks;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.tiem625.break4j.tools.fsm.State;
import com.tiem625.break4j.tools.fsm.StateId;

public class BrickBreakingState extends State {

    public static final float BRICK_BREAKING_DURATION = 3.5f;
    private final SimpleBrick entity;

    public BrickBreakingState(SimpleBrick ownerEntity) {
        super(BrickStatesIds.BRICK_BREAKING);
        this.entity = ownerEntity;
    }

    @Override
    public void enterState(StateId previousStateId) {
        entity.addAction(Actions.sequence(
                Actions.delay(BRICK_BREAKING_DURATION),
                Actions.removeActor()
        ));
    }
}

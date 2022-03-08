package com.tiem625.break4j.gdx.bricks;

import com.badlogic.gdx.graphics.Color;
import com.tiem625.break4j.model.bricks.SimpleBrick;
import com.tiem625.break4j.testhelp.GdxHeadlessSupport;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(GdxHeadlessSupport.class)
public class BrickGdxRenderTests {

    @Test
    public void create_brick_render_no_color_not_allowed() {
        assertThrows(IllegalArgumentException.class, () -> new BrickGdxRender(new SimpleBrick(), null));
    }

    @Test
    public void create_brick_render_no_model_not_allowed() {
        assertThrows(IllegalArgumentException.class, () -> new BrickGdxRender(null, Color.YELLOW));
    }

    @Test
    public void create_brick_render_is_actor_color_is_set() {

        var brickRender = assertDoesNotThrow(() -> new BrickGdxRender(new SimpleBrick(), Color.BLUE));

        assertNotNull(brickRender);
        assertEquals(Color.BLUE, brickRender.getColor());
    }

    @Test
    public void create_brick_render_has_texture_has_brick_size() {

        BrickGdxRender brickRender = new BrickGdxRender(new SimpleBrick(), Color.YELLOW);

        assertNotNull(brickRender.brickTexture());
        assertEquals(BrickGdxRender.BRICK_ONSCREEN_SIZE, brickRender.brickSize());
    }

}

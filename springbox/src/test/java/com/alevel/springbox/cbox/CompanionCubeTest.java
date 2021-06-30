package com.alevel.springbox.cbox;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CompanionCubeTest {

    private Incinerator incinerator;

    private CompanionCube companionCube;

    @BeforeEach
    void setUp() {
        incinerator = mock(Incinerator.class);
        companionCube = new CompanionCube(incinerator);
    }

    @Test
    void shouldIncinerateOnTearDown() {
        companionCube.tearDown();
        verify(incinerator, only()).burn(companionCube);
    }
}
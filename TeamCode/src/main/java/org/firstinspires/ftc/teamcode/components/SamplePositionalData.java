package org.firstinspires.ftc.teamcode.components;

import com.acmerobotics.roadrunner.Vector2d;

import java.util.Map;

public class SamplePositionalData {
    Vector2d sampleOffsetDelta;

    // HashMap voor modulariteit
    Map<SampleCategory, Vector2d> samplePositions;

    // Sample IDs geindexeerd vanaf 0
    Vector2d getSamplePosition(SampleCategory category, int sampleID) {
        if (sampleID < 0) {
            throw new RuntimeException("Negatieve sample? Waarom probeer je dit uberhaupt?");
        }

        if (this.samplePositions.containsKey(category)) {
            throw new RuntimeException("Deze categorie is niet ingeprogrammeerd! Oepsie, dat is nu een beetje te laat");
        }

        return this.samplePositions.get(category).plus(this.sampleOffsetDelta.times(sampleID));
    }
}

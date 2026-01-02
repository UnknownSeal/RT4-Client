package com.jagex.sound.midi;

public final class Midi {

    public static final int CHANNEL_COUNT = 16;

    public static final int CHANNEL_MASK = CHANNEL_COUNT - 1;

    public static final int FILE_HEADER = 0x4D546864; // "MThd"

    public static final int FORMAT_LENGTH = 2 * Byte.BYTES;

    public static final int TRACK_COUNT_LENGTH = 2 * Byte.BYTES;

    public static final int TIME_DIVISION_LENGTH = 2 * Byte.BYTES;

    public static final int FILE_BODY_LENGTH = FORMAT_LENGTH + TRACK_COUNT_LENGTH + TIME_DIVISION_LENGTH;

    public static final int TRACK_HEADER = 0x4D54726B;

}

package com.jagex.sound.midi;

import com.jagex.core.datastruct.key.IterableHashTable;
import com.jagex.js5.Js5;
import com.jagex.core.datastruct.Node;
import com.jagex.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import static com.jagex.sound.midi.Midi.*;
import static com.jagex.sound.midi.MidiController.*;
import static com.jagex.sound.midi.MidiStatus.*;
import static javax.sound.midi.ShortMessage.NOTE_ON;

@OriginalClass("runetek4.client!rf")
public final class MidiSong extends Node {

	@OriginalMember(owner = "runetek4.client!rf", name = "p", descriptor = "Lclient!sc;")
	public IterableHashTable programs;

	@OriginalMember(owner = "runetek4.client!rf", name = "q", descriptor = "[B")
	public final byte[] midiBytes;

	@OriginalMember(owner = "runetek4.client!rf", name = "<init>", descriptor = "(Lclient!wa;)V")
	public MidiSong(@OriginalArg(0) Packet packet) {
		packet.offset = packet.data.length - 3;
		@Pc(12) int tracks = packet.g1();
		@Pc(16) int timeDivision = packet.g2();
		@Pc(22) int midiLength = tracks * 10 + 14;
		packet.offset = 0;
		@Pc(27) int tempCount = 0;
		@Pc(29) int conrollerCount = 0;
		@Pc(31) int noteOnCount = 0;
		@Pc(33) int noteOffCount = 0;
		@Pc(35) int pitchBendCount = 0;
		@Pc(37) int channelAftertouchCount = 0;
		@Pc(39) int noteAftertouchCount = 0;
		@Pc(41) int bankSelectCount = 0;
		@Pc(43) int deltaTimePos;
		@Pc(48) int status;
		@Pc(52) int event;
		for (deltaTimePos = 0; deltaTimePos < tracks; deltaTimePos++) {
			status = -1;
			while (true) {
				event = packet.g1();
				if (event != status) {
					midiLength++;
				}
				status = event & 0xF;
				if (event == 7) {
					break;
				}
				if (event == 23) {
					tempCount++;
				} else if (status == 0) {
					noteOnCount++;
				} else if (status == 1) {
					noteOffCount++;
				} else if (status == 2) {
					conrollerCount++;
				} else if (status == 3) {
					pitchBendCount++;
				} else if (status == 4) {
					channelAftertouchCount++;
				} else if (status == 5) {
					noteAftertouchCount++;
				} else if (status == 6) {
					bankSelectCount++;
				} else {
					throw new RuntimeException();
				}
			}
		}
		midiLength += tempCount * 5;
		midiLength += (noteOnCount + noteOffCount + conrollerCount + pitchBendCount + noteAftertouchCount) * 2;
		midiLength += channelAftertouchCount + bankSelectCount;
		deltaTimePos = packet.offset;
		status = tracks + tempCount + conrollerCount + noteOnCount + noteOffCount + pitchBendCount + channelAftertouchCount + noteAftertouchCount + bankSelectCount;
		for (event = 0; event < status; event++) {
			packet.gVarInt();
		}
		midiLength += packet.offset - deltaTimePos;
		event = packet.offset;
		@Pc(179) int modulationWheelMsbEvents = 0;
		@Pc(181) int modulationWheelLsbEvents = 0;
		@Pc(183) int channelVolumeMsbEvents = 0;
		@Pc(185) int channelVolumeLsbEvents = 0;
		@Pc(187) int panMsbEvents = 0;
		@Pc(189) int panLsbEvents = 0;
		@Pc(191) int nonRegisteredMsbEvents = 0;
		@Pc(193) int nonRegisteredLsbEvents = 0;
		@Pc(195) int registeredMsbEvents = 0;
		@Pc(197) int registeredLsbEvents = 0;
		@Pc(199) int otherKnownControllerEvents = 0;
		@Pc(201) int unknownControllerEvents = 0;
		@Pc(203) int controllerType = 0;
		@Pc(205) int i;
		for (i = 0; i < conrollerCount; i++) {
			controllerType = controllerType + packet.g1() & 0x7F;
			if (controllerType == BANK_SELECT_COARSE || controllerType == BANK_SELECT_FINE) {
				bankSelectCount++;
			} else if (controllerType == MODULATION_WHEEL_COARSE) {
				modulationWheelMsbEvents++;
			} else if (controllerType == MODULATION_WHEEL_FINE) {
				modulationWheelLsbEvents++;
			} else if (controllerType == MAIN_VOLUME_COARSE) {
				channelVolumeMsbEvents++;
			} else if (controllerType == MAIN_VOLUME_FINE) {
				channelVolumeLsbEvents++;
			} else if (controllerType == PAN_COARSE) {
				panMsbEvents++;
			} else if (controllerType == PAN_FINE) {
				panLsbEvents++;
			} else if (controllerType == NON_REGISTERED_PARAM_FINE) {
				nonRegisteredMsbEvents++;
			} else if (controllerType == NON_REGISTERED_PARAM_COARSE) {
				nonRegisteredLsbEvents++;
			} else if (controllerType == REGISTERED_PARAM_FINE) {
				registeredMsbEvents++;
			} else if (controllerType == REGISTERED_PARAM_COARSE) {
				registeredLsbEvents++;
			} else if (controllerType == HOLD || controllerType == PORTAMENTO_PEDAL || controllerType == ALL_SOUND_OFF || controllerType == ALL_CONTROLLERS_OFF || controllerType == ALL_NOTES_OFF) {
				otherKnownControllerEvents++;
			} else {
				unknownControllerEvents++;
			}
		}
		i = 0;
		@Pc(298) int specialPos = packet.offset;
		packet.offset += otherKnownControllerEvents;
		@Pc(307) int noteAftertouchPos = packet.offset;
		packet.offset += noteAftertouchCount;
		@Pc(316) int channelAftertouchPos = packet.offset;
		packet.offset += channelAftertouchCount;
		@Pc(325) int pitchBendCoarsePos = packet.offset;
		packet.offset += pitchBendCount;
		@Pc(334) int modulationWheelMsbPos = packet.offset;
		packet.offset += modulationWheelMsbEvents;
		@Pc(343) int channelVolumeMsbPos = packet.offset;
		packet.offset += channelVolumeMsbEvents;
		@Pc(352) int panMsbPos = packet.offset;
		packet.offset += panMsbEvents;
		@Pc(361) int noteCountPos = packet.offset;
		packet.offset += noteOnCount + noteOffCount + noteAftertouchCount;
		@Pc(374) int noteOnVelocityPos = packet.offset;
		packet.offset += noteOnCount;
		@Pc(383) int otherPos = packet.offset;
		packet.offset += unknownControllerEvents;
		@Pc(392) int noteOffVelocityPos = packet.offset;
		packet.offset += noteOffCount;
		@Pc(401) int modulationWheelLsbPos = packet.offset;
		packet.offset += modulationWheelLsbEvents;
		@Pc(410) int channelVolumeLsbPos = packet.offset;
		packet.offset += channelVolumeLsbEvents;
		@Pc(419) int panLsbPos = packet.offset;
		packet.offset += panLsbEvents;
		@Pc(428) int bankSelectPos = packet.offset;
		packet.offset += bankSelectCount;
		@Pc(437) int pitchBendFinePos = packet.offset;
		packet.offset += pitchBendCount;
		@Pc(446) int nonRegisteredMsbPos = packet.offset;
		packet.offset += nonRegisteredMsbEvents;
		@Pc(455) int nonRegisteredLsbPos = packet.offset;
		packet.offset += nonRegisteredLsbEvents;
		@Pc(464) int registeredMsbPos = packet.offset;
		packet.offset += registeredMsbEvents;
		@Pc(473) int registeredLsbPos = packet.offset;
		packet.offset += registeredLsbEvents;
		@Pc(482) int tempoPos = packet.offset;
		packet.offset += tempCount * 3;
		this.midiBytes = new byte[midiLength];
		@Pc(500) Packet midiPacket = new Packet(this.midiBytes);
		midiPacket.p4(FILE_HEADER);
		midiPacket.p4(FILE_BODY_LENGTH);
		midiPacket.p2(tracks > 1 ? 1 : 0);
		midiPacket.p2(tracks);
		midiPacket.p2(timeDivision);
		packet.offset = deltaTimePos;
		@Pc(530) int channel = 0;
		@Pc(532) int noteNumber = 0;
		@Pc(534) int noteOnVelocity = 0;
		@Pc(536) int noteOffVelocity = 0;
		@Pc(538) int pitch = 0;
		@Pc(540) int channelAftertouch = 0;
		@Pc(542) int noteAftertouch = 0;
		@Pc(545) int[] controllers = new int[128];
		controllerType = 0;
		track: for (@Pc(549) int j = 0; j < tracks; j++) {
			midiPacket.p4(TRACK_HEADER);
			midiPacket.offset += 4;
			@Pc(565) int local565 = midiPacket.offset;
			@Pc(567) int lo = -1;
			while (true) {
				while (true) {
					@Pc(571) int deltaTime = packet.gVarInt();
					midiPacket.pVarInt(deltaTime);
					@Pc(583) int event2 = packet.data[i++] & 0xFF;
					@Pc(590) boolean distinct = event2 != lo;
					lo = event2 & CHANNEL_MASK;
					if (event2 == 7) {
						if (distinct) {
							midiPacket.p1(255);
						}
						midiPacket.p1(47);
						midiPacket.p1(0);
						midiPacket.p4len(midiPacket.offset - local565);
						continue track;
					}
					if (event2 == 23) {
						if (distinct) {
							midiPacket.p1(255);
						}
						midiPacket.p1(81);
						midiPacket.p1(3);
						midiPacket.p1(packet.data[tempoPos++]);
						midiPacket.p1(packet.data[tempoPos++]);
						midiPacket.p1(packet.data[tempoPos++]);
					} else {
						channel ^= event2 >> 4;
						if (lo == 0) {
							if (distinct) {
								midiPacket.p1(channel + NOTE_ON);
							}
							noteNumber += packet.data[noteCountPos++];
							noteOnVelocity += packet.data[noteOnVelocityPos++];
							midiPacket.p1(noteNumber & 0x7F);
							midiPacket.p1(noteOnVelocity & 0x7F);
						} else if (lo == 1) {
							if (distinct) {
								midiPacket.p1(channel + 128);
							}
							noteNumber += packet.data[noteCountPos++];
							noteOffVelocity += packet.data[noteOffVelocityPos++];
							midiPacket.p1(noteNumber & 0x7F);
							midiPacket.p1(noteOffVelocity & 0x7F);
						} else if (lo == 2) {
							if (distinct) {
								midiPacket.p1(channel + CONTROLLER);
							}
							controllerType = controllerType + packet.data[event++] & 0x7F;
							midiPacket.p1(controllerType);
							@Pc(775) byte valueDelta;
							if (controllerType == BANK_SELECT_COARSE || controllerType == BANK_SELECT_FINE) {
								valueDelta = packet.data[bankSelectPos++];
							} else if (controllerType == MODULATION_WHEEL_COARSE) {
								valueDelta = packet.data[modulationWheelMsbPos++];
							} else if (controllerType == MODULATION_WHEEL_FINE) {
								valueDelta = packet.data[modulationWheelLsbPos++];
							} else if (controllerType == MAIN_VOLUME_COARSE) {
								valueDelta = packet.data[channelVolumeMsbPos++];
							} else if (controllerType == MAIN_VOLUME_FINE) {
								valueDelta = packet.data[channelVolumeLsbPos++];
							} else if (controllerType == PAN_COARSE) {
								valueDelta = packet.data[panMsbPos++];
							} else if (controllerType == PAN_FINE) {
								valueDelta = packet.data[panLsbPos++];
							} else if (controllerType == NON_REGISTERED_PARAM_FINE) {
								valueDelta = packet.data[nonRegisteredMsbPos++];
							} else if (controllerType == NON_REGISTERED_PARAM_COARSE) {
								valueDelta = packet.data[nonRegisteredLsbPos++];
							} else if (controllerType == REGISTERED_PARAM_FINE) {
								valueDelta = packet.data[registeredMsbPos++];
							} else if (controllerType == REGISTERED_PARAM_COARSE) {
								valueDelta = packet.data[registeredLsbPos++];
							} else if (controllerType == HOLD || controllerType == PORTAMENTO_PEDAL || controllerType == ALL_SOUND_OFF || controllerType == ALL_CONTROLLERS_OFF || controllerType == ALL_NOTES_OFF) {
								valueDelta = packet.data[specialPos++];
							} else {
								valueDelta = packet.data[otherPos++];
							}
							@Pc(910) int controllerValue = valueDelta + controllers[controllerType];
							controllers[controllerType] = controllerValue;
							midiPacket.p1(controllerValue & 0x7F);
						} else if (lo == 3) {
							if (distinct) {
								midiPacket.p1(channel + PITCH);
							}
							pitch += packet.data[pitchBendFinePos++];
							pitch += packet.data[pitchBendCoarsePos++] << 7;
							midiPacket.p1(pitch & 0x7F);
							midiPacket.p1(pitch >> 7 & 0x7F);
						} else if (lo == 4) {
							if (distinct) {
								midiPacket.p1(channel + CHANNEL_AFTERTOUCH);
							}
							channelAftertouch += packet.data[channelAftertouchPos++];
							midiPacket.p1(channelAftertouch & 0x7F);
						} else if (lo == 5) {
							if (distinct) {
								midiPacket.p1(channel + NOTE_AFTERTOUCH);
							}
							noteNumber += packet.data[noteCountPos++];
							noteAftertouch += packet.data[noteAftertouchPos++];
							midiPacket.p1(noteNumber & 0x7F);
							midiPacket.p1(noteAftertouch & 0x7F);
						} else if (lo == 6) {
							if (distinct) {
								midiPacket.p1(channel + PROGRAM_CHANGE);
							}
							midiPacket.p1(packet.data[bankSelectPos++]);
						} else {
							throw new RuntimeException();
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!rf", name = "a", descriptor = "(Lclient!ve;II)Lclient!rf;")
	public static MidiSong create(@OriginalArg(0) Js5 archive, @OriginalArg(1) int group, @OriginalArg(2) int file) {
		@Pc(5) byte[] bytes = archive.getfile(group, file);
		return bytes == null ? null : new MidiSong(new Packet(bytes));
	}

	@OriginalMember(owner = "runetek4.client!rf", name = "a", descriptor = "()V")
	public final void resetPrograms() {
		this.programs = null;
	}

	@OriginalMember(owner = "runetek4.client!rf", name = "b", descriptor = "()V")
	public final void computePrograms() {
		if (this.programs != null) {
			return;
		}
		this.programs = new IterableHashTable(16);
		@Pc(12) int[] extPrograms = new int[16];
		@Pc(15) int[] programs = new int[16];
		extPrograms[9] = programs[9] = 128;
		@Pc(29) MidiSequence sequence = new MidiSequence(this.midiBytes);
		@Pc(32) int tracks = sequence.trackCount();
		@Pc(34) int track;
		for (track = 0; track < tracks; track++) {
			sequence.switchTrack(track);
			sequence.step(track);
			sequence.updatePosition(track);
		}
		label53: do {
			while (true) {
				track = sequence.activeTrack();
				@Pc(56) int delta = sequence.trackDeltas[track];
				while (sequence.trackDeltas[track] == delta) {
					sequence.switchTrack(track);
					@Pc(69) int event = sequence.nextEvent(track);
					if (event == 1) {
						sequence.endTrack();
						sequence.updatePosition(track);
						continue label53;
					}
					@Pc(85) int local85 = event & 0xF0;
					@Pc(92) int channel;
					@Pc(98) int controller;
					@Pc(104) int value;
					if (local85  == CONTROLLER) {
						channel = event & CHANNEL_MASK;
						controller = event >> 8 & 0x7F;
						value = event >> 16 & 0x7F;
						if (controller == 0) {
							extPrograms[channel] = (extPrograms[channel] & 0xFFE03FFF) + (value << 14);
						}
						if (controller == 32) {
							extPrograms[channel] = (extPrograms[channel] & 0xFFFFC07F) + (value << 7);
						}
					}
					if (local85 == PROGRAM_CHANGE) {
						channel = event & CHANNEL_MASK;
						controller = event >> 8 & 0x7F;
						programs[channel] = extPrograms[channel] + controller;
					}
					if (local85 == MidiStatus.NOTE_ON) {
						channel = event & CHANNEL_MASK;
						controller = event >> 8 & 0x7F;
						value = event >> 16 & 0x7F;
						if (value > 0) {
							@Pc(179) int program = programs[channel];
							@Pc(187) MidiProgramNode node = (MidiProgramNode) this.programs.get((long) program);
							if (node == null) {
								node = new MidiProgramNode(new byte[128]);
								this.programs.put(node, (long) program);
							}
							node.notes[controller] = 1;
						}
					}
					sequence.step(track);
					sequence.updatePosition(track);
				}
			}
		} while (!sequence.isComplete());
	}
}

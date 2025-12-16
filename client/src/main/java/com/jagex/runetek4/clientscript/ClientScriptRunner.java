package com.jagex.runetek4.clientscript;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

import com.jagex.runetek4.*;
import com.jagex.runetek4.graphics.animation.ProjAnimNode;
import com.jagex.runetek4.graphics.animation.ProjectileAnimation;
import com.jagex.runetek4.graphics.animation.SpotAnim;
import com.jagex.runetek4.graphics.animation.SpotAnimEntity;
import com.jagex.runetek4.audio.core.SoundPlayer;
import com.jagex.runetek4.audio.midi.MidiPlayer;
import com.jagex.runetek4.audio.streaming.MusicPlayer;
import com.jagex.runetek4.data.cache.cs.ClientScript;
import com.jagex.runetek4.scene.Camera;
import com.jagex.runetek4.scene.tile.ShapedTile;
import com.jagex.runetek4.ui.chat.Chat;
import com.jagex.runetek4.ui.chat.ClanChat;
import com.jagex.runetek4.ui.chat.OverHeadChat;
import com.jagex.runetek4.ui.chat.QuickChatPhrase;
import com.jagex.runetek4.client.*;
import com.jagex.runetek4.config.types.npc.NpcType;
import com.jagex.runetek4.config.types.obj.ObjType;
import com.jagex.runetek4.data.cache.media.Font;
import com.jagex.runetek4.data.cache.media.component.Component;
import com.jagex.runetek4.config.types.enums.EnumTypeList;
import com.jagex.runetek4.config.types.idk.IDKTypeList;
import com.jagex.runetek4.config.types.inv.InvTypeList;
import com.jagex.runetek4.config.types.loc.LocTypeList;
import com.jagex.runetek4.config.types.npc.NpcTypeList;
import com.jagex.runetek4.config.types.obj.ObjTypeList;
import com.jagex.runetek4.config.types.param.ParamType;
import com.jagex.runetek4.config.types.param.ParamTypeList;
import com.jagex.runetek4.config.types.quickchat.QuickChatCatType;
import com.jagex.runetek4.config.types.quickchat.QuickChatCatTypeList;
import com.jagex.runetek4.config.types.quickchat.QuickChatPhraseTypeList;
import com.jagex.runetek4.config.types.struct.StructTypeList;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.core.datastruct.IntWrapper;
import com.jagex.runetek4.core.exceptions.TracingException;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.config.types.enums.EnumType;
import com.jagex.runetek4.config.types.quickchat.QuickChatPhraseType;
import com.jagex.runetek4.entity.entity.*;
import com.jagex.runetek4.game.logic.Find;
import com.jagex.runetek4.game.logic.PathFinder;
import com.jagex.runetek4.game.combat.HitBarList;
import com.jagex.runetek4.game.inventory.Inv;
import com.jagex.runetek4.game.state.VarcDomain;
import com.jagex.runetek4.game.state.VarpDomain;
import com.jagex.runetek4.graphics.core.DisplayMode;
import com.jagex.runetek4.graphics.font.FontMetricsList;
import com.jagex.runetek4.graphics.font.Fonts;
import com.jagex.runetek4.graphics.gl.*;
import com.jagex.runetek4.input.Keyboard;
import com.jagex.runetek4.input.Mouse;
import com.jagex.runetek4.data.js5.Js5TextureProvider;
import com.jagex.runetek4.ui.component.SubInterface;
import com.jagex.runetek4.util.math.MathUtils;
import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.util.string.LocalizedText;
import com.jagex.runetek4.util.string.WordPack;
import com.jagex.runetek4.graphics.lighting.FogManager;
import com.jagex.runetek4.game.map.Map;
import com.jagex.runetek4.game.map.MapList;
import com.jagex.runetek4.game.map.MapMarker;
import com.jagex.runetek4.network.ClientProt;
import com.jagex.runetek4.network.Protocol;
import com.jagex.runetek4.core.node.SecondaryLinkedList;
import com.jagex.runetek4.graphics.raster.Rasterizer;
import com.jagex.runetek4.graphics.raster.SoftwareRenderer;
import com.jagex.runetek4.graphics.render.MaterialManager;
import com.jagex.runetek4.scene.SceneCamera;
import com.jagex.runetek4.scene.SceneGraph;
import com.jagex.runetek4.scene.Scenery;
import com.jagex.runetek4.scene.tile.Tile;
import com.jagex.runetek4.ui.social.FriendList;
import com.jagex.runetek4.ui.social.IgnoreList;
import com.jagex.runetek4.ui.sprite.Sprite;
import com.jagex.runetek4.ui.sprite.SpriteLoader;
import com.jagex.runetek4.ui.sprite.Sprites;
import com.jagex.runetek4.game.stockmarket.StockMarketManager;
import com.jagex.runetek4.ui.component.ComponentList;
import com.jagex.runetek4.ui.component.MiniMap;
import com.jagex.runetek4.ui.component.MiniMenu;
import com.jagex.runetek4.ui.events.ComponentEvent;
import com.jagex.runetek4.util.*;
import com.jagex.runetek4.util.data.Base37;
import com.jagex.runetek4.util.debug.Cheat;
import com.jagex.runetek4.util.string.StringUtils;
import com.jagex.runetek4.util.system.SignLink;
import com.jagex.runetek4.game.world.World;
import com.jagex.runetek4.game.world.WorldInfo;
import com.jagex.runetek4.game.world.WorldList;
import com.jagex.runetek4.game.world.WorldMap;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import static com.jagex.runetek4.network.ClientProt.*;

public final class ClientScriptRunner {

	@OriginalMember(owner = "client!bm", name = "p", descriptor = "Lclient!na;")
	public static final JString U1 = JString.parse("(U1");

	@OriginalMember(owner = "runetek4.client!wh", name = "u", descriptor = "Lclient!na;")
	public static final JString U2 = JString.parse("(U2");

	@OriginalMember(owner = "runetek4.client!mj", name = "g", descriptor = "Lclient!na;")
	public static final JString U3 = JString.parse("(U3");

	@OriginalMember(owner = "runetek4.client!hh", name = "a", descriptor = "Lclient!na;")
	public static final JString U4 = JString.parse("(U4");

	@OriginalMember(owner = "runetek4.client!tb", name = "P", descriptor = "Lclient!na;")
	public static final JString U5 = JString.parse("(U5");

	@OriginalMember(owner = "runetek4.client!jh", name = "g", descriptor = "Lclient!na;")
	public static final JString Udns = JString.parse("(Udns");

	@OriginalMember(owner = "runetek4.client!lh", name = "z", descriptor = "Lclient!na;")
	public static final JString aClass100_672 = JString.parse("(U (X");

	@OriginalMember(owner = "client!bd", name = "b", descriptor = "Lclient!na;")
	public static final JString aClass100_80 = JString.parse("(U(Y");

	@OriginalMember(owner = "runetek4.client!oj", name = "p", descriptor = "I")
	public static final int anInt4306 = 2301979;

	@OriginalMember(owner = "runetek4.client!ec", name = "l", descriptor = "I")
	public static final int anInt1704 = 5063219;

	@OriginalMember(owner = "runetek4.client!rl", name = "Z", descriptor = "I")
	public static final int anInt4938 = 7759444;

	@OriginalMember(owner = "client!bj", name = "V", descriptor = "I")
	public static final int anInt671 = 3353893;

	@OriginalMember(owner = "runetek4.client!pg", name = "V", descriptor = "I")
	public static final int anInt4504 = 50;

	@OriginalMember(owner = "runetek4.client!ab", name = "j", descriptor = "[Lclient!na;")
	public static final JString[] scriptStringValues = new JString[1000];

	@OriginalMember(owner = "runetek4.client!uj", name = "t", descriptor = "[I")
	public static final int[] scriptIntValues = new int[1000];

	@OriginalMember(owner = "client!fl", name = "Q", descriptor = "Lclient!na;")
	public static final JString EMPTY_STRING = JString.parse("");

	@OriginalMember(owner = "client!bb", name = "A", descriptor = "Lclient!na;")
	public static final JString aClass100_74 = JString.parse("::");

	@OriginalMember(owner = "client!be", name = "ib", descriptor = "Lclient!na;")
	public static final JString EVENT_OPBASE = JString.parse("event_opbase");

	@OriginalMember(owner = "client!da", name = "O", descriptor = "Lclient!na;")
	public static final JString aClass100_253 = JString.parse("(U0a )2 via: ");

	@OriginalMember(owner = "client!fl", name = "H", descriptor = "Lclient!na;")
	public static final JString aClass100_446 = JString.parse("<img=0>");

	@OriginalMember(owner = "runetek4.client!nd", name = "b", descriptor = "Lclient!na;")
	public static final JString aClass100_780 = JString.parse("Clientscript error in: ");

	@OriginalMember(owner = "runetek4.client!hm", name = "R", descriptor = "Lclient!na;")
	public static final JString aClass100_537 = JString.parse("<img=1>");

	@OriginalMember(owner = "runetek4.client!hn", name = "K", descriptor = "Ljava/util/Calendar;")
	public static final Calendar aCalendar2 = Calendar.getInstance();

	@OriginalMember(owner = "runetek4.client!kk", name = "m", descriptor = "Lclient!na;")
	public static final JString CS_ERROR = JString.parse("Clientscript error )2 check log for details");

	@OriginalMember(owner = "client!fe", name = "nc", descriptor = "[Lclient!hj;")
	public static final GoSubFrame[] callStack = new GoSubFrame[50];

	@OriginalMember(owner = "client!ee", name = "j", descriptor = "[I")
	public static final int[] anIntArray140 = new int[5];

	@OriginalMember(owner = "runetek4.client!oe", name = "i", descriptor = "[[I")
	public static final int[][] anIntArrayArray33 = new int[5][5000];

	@OriginalMember(owner = "runetek4.client!rl", name = "eb", descriptor = "Lclient!na;")
	public static final JString aClass100_928 = JString.parse("(U0a )2 in: ");

	@OriginalMember(owner = "client!fe", name = "I", descriptor = "Lclient!na;")
	public static final JString aClass100_639 = JString.parse(" ");

	@OriginalMember(owner = "client!dc", name = "M", descriptor = "Lclient!na;")
	public static final JString aClass100_268 = JString.parse(")4");

	@OriginalMember(owner = "runetek4.client!he", name = "gb", descriptor = "Lclient!na;")
	public static final JString aClass100_518 = JString.parse("www");

	@OriginalMember(owner = "client!e", name = "Tc", descriptor = "Lclient!na;")
	public static final JString aClass100_365 = JString.parse("www)2wtqa");

	@OriginalMember(owner = "runetek4.client!lk", name = "J", descriptor = "Lclient!na;")
	public static final JString aClass100_687 = JString.parse(")4p=");

	@OriginalMember(owner = "client!en", name = "x", descriptor = "Lclient!na;")
	public static final JString aClass100_424 = JString.parse("http:)4)4");

	@OriginalMember(owner = "client!gf", name = "I", descriptor = "Lclient!na;")
	public static final JString aClass100_886 = JString.parse(")3runescape)3com)4l=");

	@OriginalMember(owner = "runetek4.client!v", name = "a", descriptor = "Lclient!na;")
	public static final JString aClass100_98 = JString.parse(")4a=");

	@OriginalMember(owner = "client!ch", name = "C", descriptor = "[[I")
	public static final int[][] entityCountsPerTile = new int[104][104];

	@OriginalMember(owner = "runetek4.client!n", name = "e", descriptor = "Lclient!na;")
	public static final JString aClass100_767 = JString.parse(")2");

	@OriginalMember(owner = "runetek4.client!ob", name = "p", descriptor = "Lclient!na;")
	public static final JString aClass100_802 = JString.parse("(U0a )2 non)2existant gosub script)2num: ");

	@OriginalMember(owner = "runetek4.client!af", name = "m", descriptor = "Lclient!na;")
	public static final JString aClass100_10 = JString.parse("<br>");

	@OriginalMember(owner = "runetek4.client!em", name = "z", descriptor = "Z")
	public static boolean menuVisible = false;

	@OriginalMember(owner = "runetek4.client!d", name = "R", descriptor = "Lclient!be;")
	public static Component modalBackgroundComponent = null;

	@OriginalMember(owner = "client!bi", name = "jb", descriptor = "Z")
	public static boolean aBoolean43 = true;

	@OriginalMember(owner = "runetek4.client!k", name = "m", descriptor = "Z")
	public static boolean neverRemoveRoofs = false;

	@OriginalMember(owner = "runetek4.client!ib", name = "e", descriptor = "Lclient!be;")
	public static Component dragComponent = null;

	@OriginalMember(owner = "runetek4.client!nm", name = "W", descriptor = "Lclient!na;")
	public static JString url;

	@OriginalMember(owner = "runetek4.client!th", name = "m", descriptor = "[Lclient!be;")
	public static Component[] aClass13Array13;

	@OriginalMember(owner = "runetek4.client!k", name = "j", descriptor = "I")
	public static int anInt3126;

	@OriginalMember(owner = "runetek4.client!gf", name = "K", descriptor = "I")
	public static int anInt4696;

	@OriginalMember(owner = "runetek4.client!ac", name = "p", descriptor = "Lclient!be;")
	public static Component containerComponent = null;

	@OriginalMember(owner = "runetek4.client!km", name = "pc", descriptor = "Z")
	public static boolean isDragging = false;

	@OriginalMember(owner = "runetek4.client!gg", name = "db", descriptor = "I")
	public static int minX = -1;

	@OriginalMember(owner = "runetek4.client!nb", name = "d", descriptor = "I")
	public static int dragStartY = 0;

	@OriginalMember(owner = "runetek4.client!kd", name = "Bb", descriptor = "I")
	public static int anInt3260 = -1;

	@OriginalMember(owner = "runetek4.client!hi", name = "a", descriptor = "I")
	public static int anInt2503 = -1;

	@OriginalMember(owner = "runetek4.client!ld", name = "c", descriptor = "I")
	public static int anInt3484 = -1;

	@OriginalMember(owner = "runetek4.client!lf", name = "k", descriptor = "I")
	public static int alternateSpriteId = -1;

	@OriginalMember(owner = "runetek4.client!mh", name = "X", descriptor = "I")
	public static int defaultSpriteId = -1;

	@OriginalMember(owner = "runetek4.client!ig", name = "b", descriptor = "I")
	public static int worldMapViewportX;

	@OriginalMember(owner = "runetek4.client!ig", name = "f", descriptor = "I")
	public static int worldMapViewportY;

	@OriginalMember(owner = "runetek4.client!hc", name = "P", descriptor = "I")
	public static int mapFunctionFlashTimer;

	@OriginalMember(owner = "runetek4.client!u", name = "a", descriptor = "I")
	public static int dragStartX = 0;

	@OriginalMember(owner = "runetek4.client!re", name = "y", descriptor = "I")
	public static int dragTime;

	@OriginalMember(owner = "runetek4.client!me", name = "nb", descriptor = "I")
	public static int scriptMouseX;

	@OriginalMember(owner = "runetek4.client!em", name = "w", descriptor = "I")
	public static int scriptMouseY;

	@OriginalMember(owner = "runetek4.client!vg", name = "c", descriptor = "Z")
	public static boolean aBoolean299 = false;

	@OriginalMember(owner = "runetek4.client!wb", name = "c", descriptor = "I")
	public static int anInt5794 = -1;

	@OriginalMember(owner = "client!fc", name = "a", descriptor = "I")
	public static int overheadScreenX = -1;

	@OriginalMember(owner = "runetek4.client!jh", name = "n", descriptor = "Lclient!bd;")
	public static QuickChatPhrase activePhrase;

	@OriginalMember(owner = "runetek4.client!wf", name = "j", descriptor = "Lclient!be;")
	public static Component secondaryActiveComponent;

	@OriginalMember(owner = "runetek4.client!sg", name = "i", descriptor = "Lclient!be;")
	public static Component primaryActiveComponent;

	@OriginalMember(owner = "runetek4.client!og", name = "g", descriptor = "[Lclient!na;")
	public static JString[] stringLocals;

	@OriginalMember(owner = "runetek4.client!rh", name = "a", descriptor = "[I")
	public static int[] intLocals;

	@OriginalMember(owner = "runetek4.client!km", name = "ad", descriptor = "I")
	public static int fp = 0;

	@OriginalMember(owner = "runetek4.client!od", name = "g", descriptor = "S")
	public static short aShort25 = 256;

	@OriginalMember(owner = "client!an", name = "db", descriptor = "S")
	public static short aShort9 = 205;

	@OriginalMember(owner = "runetek4.client!mc", name = "tb", descriptor = "S")
	public static short aShort22 = 1;

	@OriginalMember(owner = "runetek4.client!ac", name = "k", descriptor = "S")
	public static short aShort1 = 32767;

	@OriginalMember(owner = "runetek4.client!nc", name = "n", descriptor = "I")
	public static int anInt4055 = 0;

	@OriginalMember(owner = "runetek4.client!tm", name = "i", descriptor = "I")
	public static int anInt5377 = 0;

	@OriginalMember(owner = "client!bn", name = "eb", descriptor = "I")
	public static int anInt773 = 0;

	@OriginalMember(owner = "client!ah", name = "n", descriptor = "I")
	public static int anInt983 = 0;

	@OriginalMember(owner = "runetek4.client!sc", name = "p", descriptor = "I")
	public static int anInt5029 = 0;

	@OriginalMember(owner = "runetek4.client!kd", name = "yb", descriptor = "S")
	public static short aShort21 = 32767;

	@OriginalMember(owner = "client!ee", name = "f", descriptor = "S")
	public static short aShort12 = 1;

	@OriginalMember(owner = "runetek4.client!kk", name = "j", descriptor = "I")
	public static int anInt3325 = 0;

	@OriginalMember(owner = "runetek4.client!vk", name = "f", descriptor = "[[[B")
	public static byte[][][] tileMarkings;

	@OriginalMember(owner = "runetek4.client!vg", name = "b", descriptor = "S")
	public static short aShort30 = 256;

	@OriginalMember(owner = "runetek4.client!lj", name = "z", descriptor = "[I")
	public static int[] anIntArray338 = new int[2];

	@OriginalMember(owner = "runetek4.client!vl", name = "i", descriptor = "[I")
	public static int[] anIntArray518 = new int[2];

	@OriginalMember(owner = "runetek4.client!tk", name = "K", descriptor = "[I")
	public static int[] anIntArray476 = new int[2];

	@OriginalMember(owner = "client!e", name = "xc", descriptor = "[I")
	public static int[] anIntArray134 = new int[2];

	@OriginalMember(owner = "client!ge", name = "k", descriptor = "[I")
	public static int[] maxHeights = new int[2];

	@OriginalMember(owner = "client!bf", name = "B", descriptor = "I")
	public static int overheadScreenY = -1;

	@OriginalMember(owner = "runetek4.client!pb", name = "rb", descriptor = "S")
	public static short aShort27 = 320;

	@OriginalMember(owner = "runetek4.client!t", name = "b", descriptor = "(I)V")
	public static void clear() {
		IDKTypeList.types.clean();
	}

	@OriginalMember(owner = "runetek4.client!ac", name = "b", descriptor = "(I)V")
	public static void handleComponentDrag() {
		ComponentList.redraw(dragComponent);
		dragTime++;
		if (ComponentList.canDrag && ComponentList.dragActive) {
			@Pc(30) int mouseX = Mouse.lastMouseX;
			mouseX -= dragStartX;
			if (minX > mouseX) {
				mouseX = minX;
			}
			@Pc(41) int mouseY = Mouse.lastMouseY;
			if (minX + containerComponent.width < mouseX - -dragComponent.width) {
				mouseX = minX + containerComponent.width - dragComponent.width;
			}
			mouseY -= dragStartY;
			if (mouseY < ComponentList.minY) {
				mouseY = ComponentList.minY;
			}
			if (ComponentList.minY + containerComponent.height < mouseY - -dragComponent.height) {
				mouseY = ComponentList.minY + containerComponent.height - dragComponent.height;
			}
			@Pc(109) int deltaY = mouseY - ComponentList.lastMouseY;
			@Pc(114) int deltaX = mouseX - ComponentList.lastMouseX;
			@Pc(122) int relativeMouseX = mouseX + containerComponent.scrollX - minX;
			@Pc(130) int relativeMouseY = containerComponent.scrollY + mouseY - ComponentList.minY;
			@Pc(133) int deadzone = dragComponent.dragDeadzone;
			if (dragTime > dragComponent.dragDeadtime && (deadzone < deltaX || -deadzone > deltaX || deltaY > deadzone || deltaY < -deadzone)) {
				isDragging = true;
			}
			@Pc(176) ComponentEvent event;
			if (dragComponent.onDragStart != null && isDragging) {
				event = new ComponentEvent();
				event.source = dragComponent;
				event.arguments = dragComponent.onDragStart;
				event.mouseX = relativeMouseX;
				event.mouseY = relativeMouseY;
				run(event);
			}
			if (Mouse.pressedButton == 0) {
				if (isDragging) {
					if (dragComponent.onDragRelease != null) {
						event = new ComponentEvent();
						event.mouseY = relativeMouseY;
						event.target = ComponentList.targetComponent;
						event.mouseX = relativeMouseX;
						event.arguments = dragComponent.onDragRelease;
						event.source = dragComponent;
						run(event);
					}
					if (ComponentList.targetComponent != null && ComponentList.canAcceptDrop(dragComponent) != null) {
						Protocol.outboundBuffer.pIsaac1(79);
						Protocol.outboundBuffer.p4_alt3(dragComponent.id);
						Protocol.outboundBuffer.p2_alt1(ComponentList.targetComponent.createdComponentId);
						Protocol.outboundBuffer.p4(ComponentList.targetComponent.id);
						Protocol.outboundBuffer.p2_alt1(dragComponent.createdComponentId);
					}
				} else if ((VarpDomain.oneMouseButton == 1 || MiniMenu.isComponentAction(MiniMenu.menuActionRow - 1)) && MiniMenu.menuActionRow > 2) {
					determineMenuSize();
				} else if (MiniMenu.menuActionRow > 0) {
					MiniMenu.processMenuActions();
				}
				dragComponent = null;
			}
		} else if (dragTime > 1) {
			dragComponent = null;
		}
	}

	@OriginalMember(owner = "runetek4.client!gi", name = "a", descriptor = "(ILclient!jl;)V")
	public static void run(@OriginalArg(1) ComponentEvent request) {
		run(200000, request);
	}

	@OriginalMember(owner = "runetek4.client!we", name = "a", descriptor = "(BILclient!be;)I")
	public static int executeClientscript(@OriginalArg(1) int scriptIndex, @OriginalArg(2) Component component) {
		if (component.scripts == null || scriptIndex >= component.scripts.length) {
			return -2;
		}
		try {
			@Pc(33) int[] script = component.scripts[scriptIndex];
			@Pc(35) byte accumulatorMode = 0;
			@Pc(37) int accumulator = 0;
			@Pc(39) int pc = 0;
			while (true) {
				@Pc(41) int value = 0;
				@Pc(46) int opcode = script[pc++];
				@Pc(48) byte nextOperator = 0;
				if (opcode == 0) {
					return accumulator;
				}
				if (opcode == 15) {
					nextOperator = 1;
				}
				if (opcode == 16) {
					nextOperator = 2;
				}
				if (opcode == 1) { // load_skill_level {skill}
					value = PlayerSkillXpTable.boostedLevels[script[pc++]];
				}
				if (opcode == 17) {
					nextOperator = 3;
				}
				if (opcode == 2) { // load_skill_base_level {skill}
					value = PlayerSkillXpTable.baseLevels[script[pc++]];
				}
				if (opcode == 3) { // load_skill_exp {skill}
					value = PlayerSkillXpTable.experience[script[pc++]];
				}
				@Pc(124) int pc2;
				@Pc(135) Component com;
				@Pc(140) int pc3;
				@Pc(152) int j;
				if (opcode == 4) { // load_inv_count {interface id} {obj id}
					pc2 = script[pc++] << 16;
					@Pc(131) int componentId = pc2 + script[pc++];
					com = ComponentList.getComponent(componentId);
					pc3 = script[pc++];
					if (pc3 != -1 && (!ObjTypeList.get(pc3).members || LoginManager.membersWorld)) {
						for (j = 0; j < com.invSlotObjId.length; j++) {
							if (pc3 + 1 == com.invSlotObjId[j]) {
								value += com.invSlotObjCount[j];
							}
						}
					}
				}
				if (opcode == 5) {  // load_var {id}
					value = VarpDomain.activeVarps[script[pc++]];
				}
				if (opcode == 6) {  // load_next_level_xp {skill}
					value = PlayerSkillXpTable.xpLevelLookup[PlayerSkillXpTable.baseLevels[script[pc++]] - 1];
				}
				if (opcode == 7) {
					value = VarpDomain.activeVarps[script[pc++]] * 100 / 46875;
				}
				if (opcode == 8) { // load_combat_level
					value = PlayerList.self.combatLevel;
				}
				if (opcode == 9) { // load_total_level
					for (pc2 = 0; pc2 < 25; pc2++) {
						if (PlayerSkillXpTable.ENABLED_SKILLS[pc2]) {
							value += PlayerSkillXpTable.baseLevels[pc2];
						}
					}
				}
				if (opcode == 10) { // load_inv_contains {interface id} {obj id}
					pc2 = script[pc++] << 16;
					pc2 += script[pc++];
					com = ComponentList.getComponent(pc2);
					pc3 = script[pc++];
					if (pc3 != -1 && (!ObjTypeList.get(pc3).members || LoginManager.membersWorld)) {
						for (j = 0; j < com.invSlotObjId.length; j++) {
							if (com.invSlotObjId[j] == pc3 + 1) {
								value = 999999999;
								break;
							}
						}
					}
				}
				if (opcode == 11) { // load_energy
					value = Player.runEnergy;
				}
				if (opcode == 12) { // load_weight
					value = Player.weightCarried;
				}
				if (opcode == 13) { // load_bool {varp} {bit: 0..31}
					pc2 = VarpDomain.activeVarps[script[pc++]];
					@Pc(353) int leastSignificantBit = script[pc++];
					value = (0x1 << leastSignificantBit & pc2) == 0 ? 0 : 1;
				}
				if (opcode == 14) {
					pc2 = script[pc++];
					value = VarpDomain.getVarbitValue(pc2);
				}
				if (opcode == 18) {
					value = (PlayerList.self.xFine >> 7) + Camera.sceneBaseTileX;
				}
				if (opcode == 19) {
					value = (PlayerList.self.zFine >> 7) + Camera.sceneBaseTileZ;
				}
				if (opcode == 20) {
					value = script[pc++];
				}
				if (nextOperator == 0) {
					if (accumulatorMode == 0) {
						accumulator += value;
					}
					if (accumulatorMode == 1) {
						accumulator -= value;
					}
					if (accumulatorMode == 2 && value != 0) {
						accumulator /= value;
					}
					if (accumulatorMode == 3) {
						accumulator *= value;
					}
					accumulatorMode = 0;
				} else {
					accumulatorMode = nextOperator;
				}
			}
		} catch (@Pc(464) Exception local464) {
			return -1;
		}
	}

	@OriginalMember(owner = "runetek4.client!md", name = "a", descriptor = "(Lclient!be;I)Z")
	public static boolean isTrue(@OriginalArg(0) Component component) {
		if (component.cs1ComparisonOpcodes == null) {
			return false;
		}
		for (@Pc(14) int i = 0; i < component.cs1ComparisonOpcodes.length; i++) {
			@Pc(34) int value = executeClientscript(i, component);
			@Pc(39) int operand = component.scriptOperand[i];
			if (component.cs1ComparisonOpcodes[i] == 2) {
				if (operand <= value) {
					return false;
				}
			} else if (component.cs1ComparisonOpcodes[i] == 3) {
				if (value <= operand) {
					return false;
				}
			} else if (component.cs1ComparisonOpcodes[i] == 4) {
				if (value == operand) {
					return false;
				}
			} else if (operand != value) {
				return false;
			}
		}
		return true;
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(BILclient!jl;)V")
	public static void run(@OriginalArg(1) int maxCycles, @OriginalArg(2) ComponentEvent request) {
		@Pc(4) Object[] listeners = request.arguments;
		@Pc(10) int sid = (Integer) listeners[0];
		@Pc(14) ClientScript clientScript = ClientScriptList.get(sid);
		if (clientScript == null) {
			return;
		}
		fp = 0;
		@Pc(26) int ssp = 0;
		@Pc(28) int isp = 0;
		@Pc(30) int pc = -1;
		@Pc(33) int[] intOperands = clientScript.intOperands;
		@Pc(36) int[] opcodes = clientScript.opcodes;
		@Pc(44) byte op = -1;
		@Pc(58) int cycles;
		try {
			intLocals = new int[clientScript.localIntCount];
			@Pc(50) int localIntIndex = 0;
			stringLocals = new JString[clientScript.localStringCount];
			@Pc(56) int localStringIndex = 0;
			@Pc(77) int id;
			@Pc(194) JString value;
			for (cycles = 1; cycles < listeners.length; cycles++) {
				if (listeners[cycles] instanceof Integer) {
					id = (Integer) listeners[cycles];
					if (id == -2147483647) { // 0
						id = request.mouseX;
					}
					if (id == -2147483646) { // 1
						id = request.mouseY;
					}
					if (id == -2147483645) { // 2
						id = request.source == null ? -1 : request.source.id;
					}
					if (id == -2147483644) { // 3
						id = request.op;
					}
					if (id == -2147483643) {
						id = request.source == null ? -1 : request.source.createdComponentId;
					}
					if (id == -2147483642) {
						id = request.target == null ? -1 : request.target.id;
					}
					if (id == -2147483641) {
						id = request.target == null ? -1 : request.target.createdComponentId;
					}
					if (id == -2147483640) {
						id = request.keyCode;
					}
					if (id == -2147483639) {
						id = request.keyChar;
					}
					intLocals[localIntIndex++] = id;
				} else if (listeners[cycles] instanceof JString) {
					value = (JString) listeners[cycles];
					if (value.strEquals(EVENT_OPBASE)) {
						value = request.opBase;
					}
					stringLocals[localStringIndex++] = value;
				}
			}
			cycles = 0;
			nextOp: while (true) {
				cycles++;
				if (maxCycles < cycles) {
					throw new RuntimeException("slow");
				}
				pc++;
				@Pc(226) int opcode = opcodes[pc];
				@Pc(803) int interfaceType;
				@Pc(652) int i;
				@Pc(809) int componentId;
				@Pc(609) JString chatTyped;
				if (opcode < 100) {
					// core language ops (not commands)

					if (opcode == 0) {
						// push_constant_int
						scriptIntValues[isp++] = intOperands[pc];
						continue;
					}
					if (opcode == 1) {
						// push_varp
						id = intOperands[pc];
						scriptIntValues[isp++] = VarpDomain.activeVarps[id];
						continue;
					}
					if (opcode == 2) {
						// pop_varp
						id = intOperands[pc];
						isp--;
						VarpDomain.setVarpClient(id, scriptIntValues[isp]);
						continue;
					}
					if (opcode == 3) {
						// push_constant_string
						scriptStringValues[ssp++] = clientScript.stringOperands[pc];
						continue;
					}
					if (opcode == 6) {
						// branch
						pc += intOperands[pc];
						continue;
					}
					if (opcode == 7) {
						// branch_not
						isp -= 2;
						if (scriptIntValues[isp] != scriptIntValues[isp + 1]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (opcode == 8) {
						// branch_equal
						isp -= 2;
						if (scriptIntValues[isp + 1] == scriptIntValues[isp]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (opcode == 9) {
						// branch_equals
						isp -= 2;
						if (scriptIntValues[isp] < scriptIntValues[isp + 1]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (opcode == 10) {
						// branch_greater_than
						isp -= 2;
						if (scriptIntValues[isp + 1] < scriptIntValues[isp]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (opcode == 21) {
						// return
						if (fp == 0) {
							return;
						}
						@Pc(423) GoSubFrame frame = callStack[--fp];
						clientScript = frame.script;
						intLocals = frame.localInts;
						opcodes = clientScript.opcodes;
						pc = frame.pc;
						stringLocals = frame.stringLocals;
						intOperands = clientScript.intOperands;
						continue;
					}
					if (opcode == 25) {
						// push_varbit
						id = intOperands[pc];
						scriptIntValues[isp++] = VarpDomain.getVarbitValue(id);
						continue;
					}
					if (opcode == 27) {
						// pop_varbit
						id = intOperands[pc];
						isp--;
						VarpDomain.setVarbitClient(id, scriptIntValues[isp]);
						continue;
					}
					if (opcode == 31) {
						// branch_less_than_or_equals
						isp -= 2;
						if (scriptIntValues[isp + 1] >= scriptIntValues[isp]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (opcode == 32) {
						// branch_greater_than_or_equals
						isp -= 2;
						if (scriptIntValues[isp] >= scriptIntValues[isp + 1]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (opcode == 33) {
						// push_int_local
						scriptIntValues[isp++] = intLocals[intOperands[pc]];
						continue;
					}
					@Pc(555) int local555;
					if (opcode == 34) {
						// pop_int_local
						local555 = intOperands[pc];
						isp--;
						intLocals[local555] = scriptIntValues[isp];
						continue;
					}
					if (opcode == 35) {
						// push_string_local
						scriptStringValues[ssp++] = stringLocals[intOperands[pc]];
						continue;
					}
					if (opcode == 36) {
						// pop_string_local
						local555 = intOperands[pc];
						ssp--;
						stringLocals[local555] = scriptStringValues[ssp];
						continue;
					}
					if (opcode == 37) {
						// join_string
						id = intOperands[pc];
						ssp -= id;
						chatTyped = JString.join(ssp, id, scriptStringValues);
						scriptStringValues[ssp++] = chatTyped;
						continue;
					}
					if (opcode == 38) {
						// pop_int_discard
						isp--;
						continue;
					}
					if (opcode == 39) {
						// pop_string_discard
						ssp--;
						continue;
					}
					if (opcode == 40) {
						// gosub_with_params
						id = intOperands[pc];
						@Pc(642) ClientScript invokeScript = ClientScriptList.get(id);
						@Pc(646) int[] invokeScriptIntLocals = new int[invokeScript.localIntCount];
						@Pc(650) JString[] invokeScriptStringLocals = new JString[invokeScript.localStringCount];
						for (i = 0; i < invokeScript.intArgs; i++) {
							invokeScriptIntLocals[i] = scriptIntValues[i + isp - invokeScript.intArgs];
						}
						for (i = 0; i < invokeScript.stringArgs; i++) {
							invokeScriptStringLocals[i] = scriptStringValues[i + ssp - invokeScript.stringArgs];
						}
						isp -= invokeScript.intArgs;
						ssp -= invokeScript.stringArgs;
						@Pc(705) GoSubFrame frame = new GoSubFrame();
						frame.stringLocals = stringLocals;
						frame.localInts = intLocals;
						frame.pc = pc;
						frame.script = clientScript;
						if (fp >= callStack.length) {
							throw new RuntimeException();
						}
						clientScript = invokeScript;
						pc = -1;
						callStack[fp++] = frame;
						intLocals = invokeScriptIntLocals;
						intOperands = invokeScript.intOperands;
						opcodes = invokeScript.opcodes;
						stringLocals = invokeScriptStringLocals;
						continue;
					}
					if (opcode == 42) {
						// push_varc_int
						scriptIntValues[isp++] = VarcDomain.varcs[intOperands[pc]];
						continue;
					}
					if (opcode == 43) {
						// pop_varc_int
						id = intOperands[pc];
						isp--;
						VarcDomain.varcs[id] = scriptIntValues[isp];
						DelayedStateChange.setVarcClient(id);
						continue;
					}
					if (opcode == 44) {
						id = intOperands[pc] >> 16;
						isp--;
						interfaceType = scriptIntValues[isp];
						componentId = intOperands[pc] & 0xFFFF;
						if (interfaceType >= 0 && interfaceType <= 5000) {
							anIntArray140[id] = interfaceType;
							@Pc(828) byte defaultValue = -1;
							if (componentId == 105) {
								defaultValue = 0;
							}
							i = 0;
							while (true) {
								if (interfaceType <= i) {
									continue nextOp;
								}
								anIntArrayArray33[id][i] = defaultValue;
								i++;
							}
						}
						throw new RuntimeException();
					}
					if (opcode == 45) {
						id = intOperands[pc];
						isp--;
						componentId = scriptIntValues[isp];
						if (componentId >= 0 && componentId < anIntArray140[id]) {
							scriptIntValues[isp++] = anIntArrayArray33[id][componentId];
							continue;
						}
						throw new RuntimeException();
					}
					if (opcode == 46) {
						id = intOperands[pc];
						isp -= 2;
						componentId = scriptIntValues[isp];
						if (componentId >= 0 && componentId < anIntArray140[id]) {
							anIntArrayArray33[id][componentId] = scriptIntValues[isp + 1];
							continue;
						}
						throw new RuntimeException();
					}
					if (opcode == 47) {
						value = VarcDomain.varcstrs[intOperands[pc]];
						if (value == null) {
							value = VarpDomain.NULL;
						}
						scriptStringValues[ssp++] = value;
						continue;
					}
					if (opcode == 48) {
						id = intOperands[pc];
						ssp--;
						VarcDomain.varcstrs[id] = scriptStringValues[ssp];
						DelayedStateChange.setVarcstrClient(id);
						continue;
					}
					if (opcode == 51) {
						@Pc(992) HashTable table = clientScript.switchTables[intOperands[pc]];
						isp--;
						@Pc(1002) IntWrapper node = (IntWrapper) table.get((long) scriptIntValues[isp]);
						if (node != null) {
							pc += node.value;
						}
						continue;
					}
				}
				@Pc(1020) boolean secondary;
				if (intOperands[pc] == 1) {
					secondary = true;
				} else {
					secondary = false;
				}
				@Pc(1182) Component createdComponent;
				@Pc(1052) int j;
				@Pc(1063) Component component;
				@Pc(1087) int childId;
				@Pc(1256) Component local1256;
				if (opcode < 300) {
					if (opcode == 100) {
						// cc_create
						isp -= 3;
						componentId = scriptIntValues[isp];
						interfaceType = scriptIntValues[isp + 1];
						j = scriptIntValues[isp + 2];
						if (interfaceType != 0) {
							component = ComponentList.getComponent(componentId);
							if (component.createdComponents == null) {
								component.createdComponents = new Component[j + 1];
							}
							if (j >= component.createdComponents.length) {
								@Pc(1085) Component[] createdComponents = new Component[j + 1];
								for (childId = 0; childId < component.createdComponents.length; childId++) {
									createdComponents[childId] = component.createdComponents[childId];
								}
								component.createdComponents = createdComponents;
							}
							if (j > 0 && component.createdComponents[j - 1] == null) {
								throw new RuntimeException("Gap at:" + (j - 1));
							}
							@Pc(1137) Component local1137 = new Component();
							local1137.if3 = true;
							local1137.createdComponentId = j;
							local1137.overlayer = local1137.id = component.id;
							local1137.type = interfaceType;
							component.createdComponents[j] = local1137;
							if (secondary) {
								secondaryActiveComponent = local1137;
							} else {
								primaryActiveComponent = local1137;
							}
							ComponentList.redraw(component);
							continue;
						}
						throw new RuntimeException();
					}
					@Pc(1204) Component component2;
					if (opcode == 101) {
						// cc_delete
						createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
						if (createdComponent.createdComponentId == -1) {
							if (!secondary) {
								throw new RuntimeException("Tried to cc_delete static active-component!");
							}
							throw new RuntimeException("Tried to .cc_delete static .active-component!");
						}
						component2 = ComponentList.getComponent(createdComponent.id);
						component2.createdComponents[createdComponent.createdComponentId] = null;
						ComponentList.redraw(component2);
						continue;
					}
					if (opcode == 102) {
						// cc_deleteall
						isp--;
						createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
						createdComponent.createdComponents = null;
						ComponentList.redraw(createdComponent);
						continue;
					}
					if (opcode == 200) {
						isp -= 2;
						componentId = scriptIntValues[isp];
						interfaceType = scriptIntValues[isp + 1];
						local1256 = ComponentList.getCreatedComponent(componentId, interfaceType);
						if (local1256 != null && interfaceType != -1) {
							scriptIntValues[isp++] = 1;
							if (secondary) {
								secondaryActiveComponent = local1256;
							} else {
								primaryActiveComponent = local1256;
							}
							continue;
						}
						scriptIntValues[isp++] = 0;
						continue;
					}
					if (opcode == 201) {
						isp--;
						componentId = scriptIntValues[isp];
						component2 = ComponentList.getComponent(componentId);
						if (component2 == null) {
							scriptIntValues[isp++] = 0;
						} else {
							scriptIntValues[isp++] = 1;
							if (secondary) {
								secondaryActiveComponent = component2;
							} else {
								primaryActiveComponent = component2;
							}
						}
						continue;
					}
				} else {
					@Pc(12388) boolean local12388;
					if (opcode < 500) {
						if (opcode == 403) {
							isp -= 2;
							interfaceType = scriptIntValues[isp + 1];
							componentId = scriptIntValues[isp];
							for (j = 0; j < PlayerAppearance.MALE_FEATURES.length; j++) {
								if (componentId == PlayerAppearance.MALE_FEATURES[j]) {
									PlayerList.self.appearance.setIdentikit(j, interfaceType);
									continue nextOp;
								}
							}
							j = 0;
							while (true) {
								if (j >= PlayerAppearance.FEMALE_FEATURES.length) {
									continue nextOp;
								}
								if (componentId == PlayerAppearance.FEMALE_FEATURES[j]) {
									PlayerList.self.appearance.setIdentikit(j, interfaceType);
									continue nextOp;
								}
								j++;
							}
						}
						if (opcode == 404) {
							isp -= 2;
							componentId = scriptIntValues[isp];
							interfaceType = scriptIntValues[isp + 1];
							PlayerList.self.appearance.setColor(componentId, interfaceType);
							continue;
						}
						if (opcode == 410) {
							isp--;
							local12388 = scriptIntValues[isp] != 0;
							PlayerList.self.appearance.setGender(local12388);
							continue;
						}
					} else {
						@Pc(1552) boolean local1552;
						if ((opcode < 1000 || opcode >= 1100) && (opcode < 2000 || opcode >= 2100)) {
							@Pc(2522) JString chatTypedLowercase;
							if (opcode >= 1100 && opcode < 1200 || !(opcode < 2100 || opcode >= 2200)) {
								if (opcode < 2000) {
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
								} else {
									opcode -= 1000;
									isp--;
									createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
								}
								if (opcode == 1100) {
									// setscrollpos
									isp -= 2;
									createdComponent.scrollX = scriptIntValues[isp];
									if (createdComponent.scrollX > createdComponent.scrollMaxH - createdComponent.width) {
										createdComponent.scrollX = createdComponent.scrollMaxH - createdComponent.width;
									}
									if (createdComponent.scrollX < 0) {
										createdComponent.scrollX = 0;
									}
									createdComponent.scrollY = scriptIntValues[isp + 1];
									if (createdComponent.scrollY > createdComponent.scrollMaxV - createdComponent.height) {
										createdComponent.scrollY = createdComponent.scrollMaxV - createdComponent.height;
									}
									if (createdComponent.scrollY < 0) {
										createdComponent.scrollY = 0;
									}
									ComponentList.redraw(createdComponent);
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentScrollPositionClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == 1101) {
									// setcolor
									isp--;
									createdComponent.color = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentColorClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == 1102) {
									// setfill
									isp--;
									createdComponent.filled = scriptIntValues[isp] == 1;
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1103) {
									// settrans
									isp--;
									createdComponent.alpha = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1104) {
									// setlinewid
									isp--;
									createdComponent.lineWidth = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1105) {
									// setgraphic
									isp--;
									createdComponent.spriteId = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1106) {
									isp--;
									createdComponent.angle2d = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1107) {
									// settiling
									isp--;
									createdComponent.spriteTiling = scriptIntValues[isp] == 1;
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1108) {
									// setmodel
									createdComponent.modelType = 1;
									isp--;
									createdComponent.modelId = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentModelClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == 1109) {
									// setmodelangle
									isp -= 6;
									createdComponent.modelXOffset = scriptIntValues[isp];
									createdComponent.modelZOffset = scriptIntValues[isp + 1];
									createdComponent.modelXAngle = scriptIntValues[isp + 2];
									createdComponent.modelYAngle = scriptIntValues[isp + 3];
									createdComponent.modelYOffset = scriptIntValues[isp + 4];
									createdComponent.modelZoom = scriptIntValues[isp + 5];
									ComponentList.redraw(createdComponent);
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentModelAngleClient(createdComponent.id);
										DelayedStateChange.setComponentModelOffsetClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == 1110) {
									// setmodelanim
									isp--;
									interfaceType = scriptIntValues[isp];
									if (createdComponent.modelSeqId != interfaceType) {
										createdComponent.modelSeqId = interfaceType;
										createdComponent.animationId = 0;
										createdComponent.animationDelay = 0;
										createdComponent.animationFrame = 1;
										ComponentList.redraw(createdComponent);
									}
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentModelAnimClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == 1111) {
									// setmodelorthog
									isp--;
									createdComponent.modelOrtho = scriptIntValues[isp] == 1;
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1112) {
									// settext
									ssp--;
									chatTypedLowercase = scriptStringValues[ssp];
									if (!chatTypedLowercase.strEquals(createdComponent.text)) {
										createdComponent.text = chatTypedLowercase;
										ComponentList.redraw(createdComponent);
									}
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentTextClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == 1113) {
									// settextfont
									isp--;
									createdComponent.fontId = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1114) {
									// settextalign
									isp -= 3;
									createdComponent.halign = scriptIntValues[isp];
									createdComponent.valign = scriptIntValues[isp + 1];
									createdComponent.vpadding = scriptIntValues[isp + 2];
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1115) {
									// settextshadow
									isp--;
									createdComponent.shadowed = scriptIntValues[isp] == 1;
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1116) {
									isp--;
									createdComponent.outlineThickness = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1117) {
									isp--;
									createdComponent.shadowColor = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1118) {
									isp--;
									createdComponent.vFlip = scriptIntValues[isp] == 1;
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1119) {
									isp--;
									createdComponent.hFlip = scriptIntValues[isp] == 1;
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1120) {
									isp -= 2;
									createdComponent.scrollMaxH = scriptIntValues[isp];
									createdComponent.scrollMaxV = scriptIntValues[isp + 1];
									ComponentList.redraw(createdComponent);
									if (createdComponent.type == 0) {
										ComponentList.updateContainerLayout(createdComponent, false);
									}
									continue;
								}
								if (opcode == 1121) {
									isp -= 2;
									createdComponent.aShort11 = (short) scriptIntValues[isp];
									createdComponent.aShort10 = (short) scriptIntValues[isp + 1];
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1122) {
									isp--;
									createdComponent.hasAlpha = scriptIntValues[isp] == 1;
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1123) {
									isp--;
									createdComponent.modelZoom = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentModelAngleClient(createdComponent.id);
									}
									continue;
								}
							} else if (opcode >= 1200 && opcode < 1300 || !(opcode < 2200 || opcode >= 2300)) {
								if (opcode < 2000) {
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
								} else {
									isp--;
									createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
									opcode -= 1000;
								}
								ComponentList.redraw(createdComponent);
								if (opcode == 1200 || opcode == 1205) {
									isp -= 2;
									j = scriptIntValues[isp + 1];
									interfaceType = scriptIntValues[isp];
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentObjClient(createdComponent.id);
										DelayedStateChange.setComponentModelAngleClient(createdComponent.id);
										DelayedStateChange.setComponentModelOffsetClient(createdComponent.id);
									}
									if (interfaceType == -1) {
										createdComponent.modelId = -1;
										createdComponent.modelType = 1;
										createdComponent.objId = -1;
									} else {
										createdComponent.objId = interfaceType;
										createdComponent.objCount = j;
										@Pc(13416) ObjType local13416 = ObjTypeList.get(interfaceType);
										createdComponent.modelYOffset = local13416.zAngle2D;
										createdComponent.modelXOffset = local13416.xof2d;
										createdComponent.modelXAngle = local13416.xan2d;
										createdComponent.modelZOffset = local13416.yof2d;
										createdComponent.modelYAngle = local13416.yan2d;
										createdComponent.modelZoom = local13416.zoom2d;
										if (createdComponent.anInt451 > 0) {
											createdComponent.modelZoom = createdComponent.modelZoom * 32 / createdComponent.anInt451;
										} else if (createdComponent.baseWidth > 0) {
											createdComponent.modelZoom = createdComponent.modelZoom * 32 / createdComponent.baseWidth;
										}
										if (opcode == 1205) {
											createdComponent.objDrawText = false;
										} else {
											createdComponent.objDrawText = true;
										}
									}
									continue;
								}
								if (opcode == 1201) {
									// setnpchead
									createdComponent.modelType = 2;
									isp--;
									createdComponent.modelId = scriptIntValues[isp];
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentModelClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == 1202) {
									// setplayerhead_self
									createdComponent.modelType = 3;
									createdComponent.modelId = PlayerList.self.appearance.getHeadModelId();
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentModelClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == 1203) {
									// setnpcmodel
									createdComponent.modelType = 6;
									isp--;
									createdComponent.modelId = scriptIntValues[isp];
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentModelClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == 1204) {
									createdComponent.modelType = 5;
									isp--;
									createdComponent.modelId = scriptIntValues[isp];
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentModelClient(createdComponent.id);
									}
									continue;
								}
							} else if (opcode >= 1300 && opcode < 1400 || opcode >= 2300 && opcode < 2400) {
								if (opcode >= 2000) {
									// if_
									isp--;
									createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
									opcode -= 1000;
								} else {
									// cc_
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
								}
								if (opcode == 1300) {
									isp--;
									interfaceType = scriptIntValues[isp] - 1;
									if (interfaceType >= 0 && interfaceType <= 9) {
										ssp--;
										createdComponent.method480(scriptStringValues[ssp], interfaceType);
										continue;
									}
									ssp--;
									continue;
								}
								if (opcode == 1301) {
									isp -= 2;
									j = scriptIntValues[isp + 1];
									interfaceType = scriptIntValues[isp];
									createdComponent.parent = ComponentList.getCreatedComponent(interfaceType, j);
									continue;
								}
								if (opcode == 1302) {
									isp--;
									createdComponent.dragRenderBehavior = scriptIntValues[isp] == 1;
									continue;
								}
								if (opcode == 1303) {
									isp--;
									createdComponent.dragDeadzone = scriptIntValues[isp];
									continue;
								}
								if (opcode == 1304) {
									isp--;
									createdComponent.dragDeadtime = scriptIntValues[isp];
									continue;
								}
								if (opcode == 1305) {
									ssp--;
									createdComponent.optionBase = scriptStringValues[ssp];
									continue;
								}
								if (opcode == 1306) {
									ssp--;
									createdComponent.optionCircumfix = scriptStringValues[ssp];
									continue;
								}
								if (opcode == 1307) {
									createdComponent.ops = null;
									continue;
								}
								if (opcode == 1308) {
									isp--;
									createdComponent.anInt484 = scriptIntValues[isp];
									isp--;
									createdComponent.anInt499 = scriptIntValues[isp];
									continue;
								}
								if (opcode == 1309) {
									isp--;
									interfaceType = scriptIntValues[isp];
									isp--;
									j = scriptIntValues[isp];
									if (j >= 1 && j <= 10) {
										createdComponent.method477(j - 1, interfaceType);
									}
									continue;
								}
							} else {
								@Pc(4859) int start;
								if (opcode >= 1400 && opcode < 1500 || opcode >= 2400 && opcode < 2500) {
									if (opcode < 2000) {
										// if_
										createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
									} else {
										// cc_
										opcode -= 1000;
										isp--;
										createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
									}
									@Pc(12937) int[] local12937 = null;
									ssp--;
									chatTypedLowercase = scriptStringValues[ssp];
									if (chatTypedLowercase.length() > 0 && chatTypedLowercase.charAt(chatTypedLowercase.length() - 1) == 89) {
										isp--;
										i = scriptIntValues[isp];
										if (i > 0) {
											local12937 = new int[i];
											while (i-- > 0) {
												isp--;
												local12937[i] = scriptIntValues[isp];
											}
										}
										chatTypedLowercase = chatTypedLowercase.substring(chatTypedLowercase.length() - 1, 0);
									}
									@Pc(13000) Object[] arguments = new Object[chatTypedLowercase.length() + 1];
									for (start = arguments.length - 1; start >= 1; start--) {
										if (chatTypedLowercase.charAt(start - 1) == 115) {
											ssp--;
											arguments[start] = scriptStringValues[ssp];
										} else {
											isp--;
											arguments[start] = Integer.valueOf(scriptIntValues[isp]);
										}
									}
									isp--;
									start = scriptIntValues[isp];
									if (start == -1) {
										arguments = null;
									} else {
										arguments[0] = Integer.valueOf(start);
									}
									createdComponent.interactive = true;
									if (opcode == 1400) {
										createdComponent.onClickRepeat = arguments;
									} else if (opcode == 1401) {
										createdComponent.onHold = arguments;
									} else if (opcode == 1402) {
										createdComponent.onRelease = arguments;
									} else if (opcode == 1403) {
										createdComponent.onMouseOver = arguments;
									} else if (opcode == 1404) {
										createdComponent.onMouseLeave = arguments;
									} else if (opcode == 1405) {
										createdComponent.onDragStart = arguments;
									} else if (opcode == 1406) {
										createdComponent.onUseWith = arguments;
									} else if (opcode == 1407) {
										createdComponent.varpTriggers = local12937;
										createdComponent.onVarpTransmit = arguments;
									} else if (opcode == 1408) {
										createdComponent.onTimer = arguments;
									} else if (opcode == 1409) {
										createdComponent.onOptionClick = arguments;
									} else if (opcode == 1410) {
										createdComponent.onDragRelease = arguments;
									} else if (opcode == 1411) {
										createdComponent.onDrag = arguments;
									} else if (opcode == 1412) {
										createdComponent.onMouseRepeat = arguments;
									} else if (opcode == 1414) {
										createdComponent.inventoryTriggers = local12937;
										createdComponent.onInvTransmit = arguments;
									} else if (opcode == 1415) {
										createdComponent.statTriggers = local12937;
										createdComponent.onStatTransmit = arguments;
									} else if (opcode == 1416) {
										createdComponent.onUse = arguments;
									} else if (opcode == 1417) {
										createdComponent.onScroll = arguments;
									} else if (opcode == 1418) {
										createdComponent.onMsg = arguments;
									} else if (opcode == 1419) {
										createdComponent.onKey = arguments;
									} else if (opcode == 1420) {
										createdComponent.onFriendTransmit = arguments;
									} else if (opcode == 1421) {
										createdComponent.onClanTransmit = arguments;
									} else if (opcode == 1422) {
										createdComponent.onMiscTransmit = arguments;
									} else if (opcode == 1423) {
										createdComponent.onDialogAbort = arguments;
									} else if (opcode == 1424) {
										createdComponent.onComponentsOpenClose = arguments;
									} else if (opcode == 1425) {
										createdComponent.onStockTransmit = arguments;
									} else if (opcode == 1426) {
										createdComponent.onMinimapUnlock = arguments;
									} else if (opcode == 1427) {
										createdComponent.onResize = arguments;
									} else if (opcode == 1428) {
										createdComponent.onVarcTransmit = arguments;
										createdComponent.varcTriggers = local12937;
									} else if (opcode == 1429) {
										createdComponent.varcstrTriggers = local12937;
										createdComponent.onVarcstrTransmit = arguments;
									}
									continue;
								}
								if (opcode < 1600) {
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
									if (opcode == 1500) {
										// cc_getx
										scriptIntValues[isp++] = createdComponent.x;
										continue;
									}
									if (opcode == 1501) {
										// cc_gety
										scriptIntValues[isp++] = createdComponent.y;
										continue;
									}
									if (opcode == 1502) {
										// cc_getwidth
										scriptIntValues[isp++] = createdComponent.width;
										continue;
									}
									if (opcode == 1503) {
										// cc_getheight
										scriptIntValues[isp++] = createdComponent.height;
										continue;
									}
									if (opcode == 1504) {
										// cc_gethide
										scriptIntValues[isp++] = createdComponent.hidden ? 1 : 0;
										continue;
									}
									if (opcode == 1505) {
										// set_getlayer
										scriptIntValues[isp++] = createdComponent.overlayer;
										continue;
									}
								} else if (opcode < 1700) {
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
									if (opcode == 1600) {
										// cc_getscrollx
										scriptIntValues[isp++] = createdComponent.scrollX;
										continue;
									}
									if (opcode == 1601) {
										// cc_getscrolly
										scriptIntValues[isp++] = createdComponent.scrollY;
										continue;
									}
									if (opcode == 1602) {
										scriptStringValues[ssp++] = createdComponent.text;
										continue;
									}
									if (opcode == 1603) {
										scriptIntValues[isp++] = createdComponent.scrollMaxH;
										continue;
									}
									if (opcode == 1604) {
										scriptIntValues[isp++] = createdComponent.scrollMaxV;
										continue;
									}
									if (opcode == 1605) {
										scriptIntValues[isp++] = createdComponent.modelZoom;
										continue;
									}
									if (opcode == 1606) {
										scriptIntValues[isp++] = createdComponent.modelXAngle;
										continue;
									}
									if (opcode == 1607) {
										scriptIntValues[isp++] = createdComponent.modelYOffset;
										continue;
									}
									if (opcode == 1608) {
										scriptIntValues[isp++] = createdComponent.modelYAngle;
										continue;
									}
									if (opcode == 1609) {
										scriptIntValues[isp++] = createdComponent.alpha;
										continue;
									}
									if (opcode == 1610) {
										scriptIntValues[isp++] = createdComponent.modelXOffset;
										continue;
									}
									if (opcode == 1611) {
										scriptIntValues[isp++] = createdComponent.modelZOffset;
										continue;
									}
									if (opcode == 1612) {
										scriptIntValues[isp++] = createdComponent.spriteId;
										continue;
									}
								} else if (opcode < 1800) {
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
									if (opcode == 1700) {
										scriptIntValues[isp++] = createdComponent.objId;
										continue;
									}
									if (opcode == 1701) {
										if (createdComponent.objId == -1) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = createdComponent.objCount;
										}
										continue;
									}
									if (opcode == 1702) {
										scriptIntValues[isp++] = createdComponent.createdComponentId;
										continue;
									}
								} else if (opcode < 1900) {
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
									if (opcode == 1800) {
										scriptIntValues[isp++] = ComponentList.getServerActiveProperties(createdComponent).getTargetMask();
										continue;
									}
									if (opcode == 1801) {
										isp--;
										interfaceType = scriptIntValues[isp];
										interfaceType--;
										if (createdComponent.ops != null && interfaceType < createdComponent.ops.length && createdComponent.ops[interfaceType] != null) {
											scriptStringValues[ssp++] = createdComponent.ops[interfaceType];
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == 1802) {
										if (createdComponent.optionBase == null) {
											scriptStringValues[ssp++] = EMPTY_STRING;
										} else {
											scriptStringValues[ssp++] = createdComponent.optionBase;
										}
										continue;
									}
								} else if (opcode < 2600) {
									isp--;
									createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
									if (opcode == 2500) {
										// if_getx
										scriptIntValues[isp++] = createdComponent.x;
										continue;
									}
									if (opcode == 2501) {
										// if_gety
										scriptIntValues[isp++] = createdComponent.y;
										continue;
									}
									if (opcode == 2502) {
										// if_getwidth
										scriptIntValues[isp++] = createdComponent.width;
										continue;
									}
									if (opcode == 2503) {
										// if_getheight
										scriptIntValues[isp++] = createdComponent.height;
										continue;
									}
									if (opcode == 2504) {
										// if_gethide
										scriptIntValues[isp++] = createdComponent.hidden ? 1 : 0;
										continue;
									}
									if (opcode == 2505) {
										// if_getlayer
										scriptIntValues[isp++] = createdComponent.overlayer;
										continue;
									}
								} else if (opcode < 2700) {
									isp--;
									createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
									if (opcode == 2600) {
										// if_getscrollx
										scriptIntValues[isp++] = createdComponent.scrollX;
										continue;
									}
									if (opcode == 2601) {
										// if_getscrolly
										scriptIntValues[isp++] = createdComponent.scrollY;
										continue;
									}
									if (opcode == 2602) {
										scriptStringValues[ssp++] = createdComponent.text;
										continue;
									}
									if (opcode == 2603) {
										scriptIntValues[isp++] = createdComponent.scrollMaxH;
										continue;
									}
									if (opcode == 2604) {
										scriptIntValues[isp++] = createdComponent.scrollMaxV;
										continue;
									}
									if (opcode == 2605) {
										scriptIntValues[isp++] = createdComponent.modelZoom;
										continue;
									}
									if (opcode == 2606) {
										scriptIntValues[isp++] = createdComponent.modelXAngle;
										continue;
									}
									if (opcode == 2607) {
										scriptIntValues[isp++] = createdComponent.modelYOffset;
										continue;
									}
									if (opcode == 2608) {
										scriptIntValues[isp++] = createdComponent.modelYAngle;
										continue;
									}
									if (opcode == 2609) {
										scriptIntValues[isp++] = createdComponent.alpha;
										continue;
									}
									if (opcode == 2610) {
										scriptIntValues[isp++] = createdComponent.modelXOffset;
										continue;
									}
									if (opcode == 2611) {
										scriptIntValues[isp++] = createdComponent.modelZOffset;
										continue;
									}
									if (opcode == 2612) {
										scriptIntValues[isp++] = createdComponent.spriteId;
										continue;
									}
								} else if (opcode < 2800) {
									if (opcode == 2700) {
										isp--;
										createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
										scriptIntValues[isp++] = createdComponent.objId;
										continue;
									}
									if (opcode == 2701) {
										isp--;
										createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
										if (createdComponent.objId == -1) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = createdComponent.objCount;
										}
										continue;
									}
									if (opcode == 2702) {
										isp--;
										componentId = scriptIntValues[isp];
										@Pc(12566) SubInterface SubInterface = (SubInterface) ComponentList.openInterfaces.get((long) componentId);
										if (SubInterface == null) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = 1;
										}
										continue;
									}
									if (opcode == 2703) {
										isp--;
										createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
										if (createdComponent.createdComponents == null) {
											scriptIntValues[isp++] = 0;
											continue;
										}
										interfaceType = createdComponent.createdComponents.length;
										for (j = 0; j < createdComponent.createdComponents.length; j++) {
											if (createdComponent.createdComponents[j] == null) {
												interfaceType = j;
												break;
											}
										}
										scriptIntValues[isp++] = interfaceType;
										continue;
									}
									if (opcode == 2704 || opcode == 2705) {
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										@Pc(12663) SubInterface SubInterface = (SubInterface) ComponentList.openInterfaces.get((long) componentId);
										if (SubInterface != null && SubInterface.interfaceId == interfaceType) {
											scriptIntValues[isp++] = 1;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
								} else if (opcode < 2900) {
									isp--;
									createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
									if (opcode == 2800) {
										scriptIntValues[isp++] = ComponentList.getServerActiveProperties(createdComponent).getTargetMask();
										continue;
									}
									if (opcode == 2801) {
										isp--;
										interfaceType = scriptIntValues[isp];
										interfaceType--;
										if (createdComponent.ops != null && createdComponent.ops.length > interfaceType && createdComponent.ops[interfaceType] != null) {
											scriptStringValues[ssp++] = createdComponent.ops[interfaceType];
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == 2802) {
										if (createdComponent.optionBase == null) {
											scriptStringValues[ssp++] = EMPTY_STRING;
										} else {
											scriptStringValues[ssp++] = createdComponent.optionBase;
										}
										continue;
									}
								} else if (opcode < 3200) {
									if (opcode == 3100) {
										// mes
										ssp--;
										chatTyped = scriptStringValues[ssp];
										Chat.addMessage(EMPTY_STRING, 0, chatTyped);
										continue;
									}
									if (opcode == 3101) {
										// anim
										isp -= 2;
										Player.animate(scriptIntValues[isp + 1], scriptIntValues[isp], PlayerList.self);
										continue;
									}
									if (opcode == 3103) {
										ComponentList.closeModal();
										continue;
									}
									if (opcode == 3104) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										interfaceType = 0;
										if (chatTyped.isInt()) {
											interfaceType = chatTyped.parseInt();
										}
										Protocol.outboundBuffer.pIsaac1(23);
										Protocol.outboundBuffer.p4(interfaceType);
										continue;
									}
									if (opcode == 3105) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										Protocol.outboundBuffer.pIsaac1(244);
										Protocol.outboundBuffer.p8(chatTyped.encode37());
										continue;
									}
									if (opcode == 3106) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										Protocol.outboundBuffer.pIsaac1(65);
										Protocol.outboundBuffer.p1(chatTyped.length() + 1);
										Protocol.outboundBuffer.pjstr(chatTyped);
										continue;
									}
									if (opcode == 3107) {
										isp--;
										componentId = scriptIntValues[isp];
										ssp--;
										chatTypedLowercase = scriptStringValues[ssp];
										ClientProt.clickPlayerOption(componentId, chatTypedLowercase);
										continue;
									}
									if (opcode == 3108) {
										isp -= 3;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										j = scriptIntValues[isp + 2];
										component = ComponentList.getComponent(j);
										startComponentDrag(interfaceType, componentId, component);
										continue;
									}
									if (opcode == 3109) {
										isp -= 2;
										componentId = scriptIntValues[isp];
										local1256 = secondary ? secondaryActiveComponent : primaryActiveComponent;
										interfaceType = scriptIntValues[isp + 1];
										startComponentDrag(interfaceType, componentId, local1256);
										continue;
									}
									if (opcode == 3110) {
										isp--;
										componentId = scriptIntValues[isp];
										Protocol.outboundBuffer.pIsaac1(111);
										Protocol.outboundBuffer.p2(componentId);
										continue;
									}
								} else if (opcode < 3300) {
									if (opcode == 3200) {
										// sound_synth
										isp -= 3;
										SoundPlayer.play(scriptIntValues[isp + 1], scriptIntValues[isp], scriptIntValues[isp + 2]);
										continue;
									}
									if (opcode == 3201) {
										// sound_song
										isp--;
										MusicPlayer.playSong(scriptIntValues[isp]);
										continue;
									}
									if (opcode == 3202) {
										// sound_jingle
										isp -= 2;
										MusicPlayer.playJingle(scriptIntValues[isp + 1], scriptIntValues[isp]);
										continue;
									}
								} else if (opcode < 3400) {
									if (opcode == 3300) {
										// clientclock
										scriptIntValues[isp++] = Client.loop;
										continue;
									}
									if (opcode == 3301) {
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = Inv.getItemType(componentId, interfaceType);
										continue;
									}
									if (opcode == 3302) {
										isp -= 2;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = Inv.getItemCount(componentId, interfaceType);
										continue;
									}
									if (opcode == 3303) {
										isp -= 2;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = Inv.getSlotTotal(componentId, interfaceType);
										continue;
									}
									if (opcode == 3304) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = InvTypeList.get(componentId).size;
										continue;
									}
									if (opcode == 3305) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = PlayerSkillXpTable.boostedLevels[componentId];
										continue;
									}
									if (opcode == 3306) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = PlayerSkillXpTable.baseLevels[componentId];
										continue;
									}
									if (opcode == 3307) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = PlayerSkillXpTable.experience[componentId];
										continue;
									}
									if (opcode == 3308) {
										componentId = Player.plane;
										interfaceType = Camera.sceneBaseTileX + (PlayerList.self.xFine >> 7);
										j = (PlayerList.self.zFine >> 7) + Camera.sceneBaseTileZ;
										scriptIntValues[isp++] = (componentId << 28) - (-(interfaceType << 14) - j);
										continue;
									}
									if (opcode == 3309) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = componentId >> 14 & 0x3FFF;
										continue;
									}
									if (opcode == 3310) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = componentId >> 28;
										continue;
									}
									if (opcode == 3311) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = componentId & 0x3FFF;
										continue;
									}
									if (opcode == 3312) {
										scriptIntValues[isp++] = LoginManager.membersWorld ? 1 : 0;
										continue;
									}
									if (opcode == 3313) {
										isp -= 2;
										componentId = scriptIntValues[isp] + 32768;
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = Inv.getItemType(componentId, interfaceType);
										continue;
									}
									if (opcode == 3314) {
										isp -= 2;
										componentId = scriptIntValues[isp] + 32768;
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = Inv.getItemCount(componentId, interfaceType);
										continue;
									}
									if (opcode == 3315) {
										isp -= 2;
										componentId = scriptIntValues[isp] + 32768;
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = Inv.getSlotTotal(componentId, interfaceType);
										continue;
									}
									if (opcode == 3316) {
										if (LoginManager.staffModLevel < 2) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = LoginManager.staffModLevel;
										}
										continue;
									}
									if (opcode == 3317) {
										scriptIntValues[isp++] = Player.systemUpdateTimer;
										continue;
									}
									if (opcode == 3318) {
										scriptIntValues[isp++] = Player.worldId;
										continue;
									}
									if (opcode == 3321) {
										scriptIntValues[isp++] = Player.runEnergy;
										continue;
									}
									if (opcode == 3322) {
										scriptIntValues[isp++] = Player.weightCarried;
										continue;
									}
									if (opcode == 3323) {
										if (LoginManager.playerModLevel >= 5 && LoginManager.playerModLevel <= 9) {
											scriptIntValues[isp++] = 1;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == 3324) {
										if (LoginManager.playerModLevel >= 5 && LoginManager.playerModLevel <= 9) {
											scriptIntValues[isp++] = LoginManager.playerModLevel;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == 3325) {
										scriptIntValues[isp++] = LoginManager.playerMember ? 1 : 0;
										continue;
									}
									if (opcode == 3326) {
										scriptIntValues[isp++] = PlayerList.self.combatLevel;
										continue;
									}
									if (opcode == 3327) {
										scriptIntValues[isp++] = PlayerList.self.appearance.gender ? 1 : 0;
										continue;
									}
									if (opcode == 3328) {
										scriptIntValues[isp++] = LoginManager.playerUnderage && !LoginManager.parentalChatConsent ? 1 : 0;
										continue;
									}
									if (opcode == 3329) {
										scriptIntValues[isp++] = LoginManager.worldQuickChat ? 1 : 0;
										continue;
									}
									if (opcode == 3330) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = Inv.getFreeSpace(componentId);
										continue;
									}
									if (opcode == 3331) {
										isp -= 2;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = Inv.getTotalParam(false, componentId, interfaceType);
										continue;
									}
									if (opcode == 3332) {
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = Inv.getTotalParam(true, componentId, interfaceType);
										continue;
									}
									if (opcode == 3333) {
										scriptIntValues[isp++] = LoginManager.anInt39;
										continue;
									}
									if (opcode == 3335) {
										scriptIntValues[isp++] = Client.language;
										continue;
									}
									if (opcode == 3336) {
										isp -= 4;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										componentId += interfaceType << 14;
										i = scriptIntValues[isp + 3];
										j = scriptIntValues[isp + 2];
										componentId += j << 28;
										componentId += i;
										scriptIntValues[isp++] = componentId;
										continue;
									}
									if (opcode == 3337) {
										scriptIntValues[isp++] = Client.affiliate;
										continue;
									}
								} else if (opcode < 3500) {
									@Pc(3422) EnumType type;
									if (opcode == 3400) {
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										type = EnumTypeList.get(componentId);
										if (type.valueType == 115) {
										}
										scriptStringValues[ssp++] = type.getValueString(interfaceType);
										continue;
									}
									if (opcode == 3408) {
										isp -= 4;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										i = scriptIntValues[isp + 3];
										j = scriptIntValues[isp + 2];
										@Pc(3469) EnumType local3469 = EnumTypeList.get(j);
										if (local3469.keyType == componentId && local3469.valueType == interfaceType) {
											if (interfaceType == 115) {
												scriptStringValues[ssp++] = local3469.getValueString(i);
											} else {
												scriptIntValues[isp++] = local3469.getValueInt(i);
											}
											continue;
										}
										throw new RuntimeException("C3408-1");
									}
									if (opcode == 3409) {
										isp -= 3;
										interfaceType = scriptIntValues[isp + 1];
										j = scriptIntValues[isp + 2];
										componentId = scriptIntValues[isp];
										if (interfaceType == -1) {
											throw new RuntimeException("C3409-2");
										}
										@Pc(3549) EnumType local3549 = EnumTypeList.get(interfaceType);
										if (local3549.valueType != componentId) {
											throw new RuntimeException("C3409-1");
										}
										scriptIntValues[isp++] = local3549.containsValue(j) ? 1 : 0;
										continue;
									}
									if (opcode == 3410) {
										isp--;
										componentId = scriptIntValues[isp];
										ssp--;
										chatTypedLowercase = scriptStringValues[ssp];
										if (componentId == -1) {
											throw new RuntimeException("C3410-2");
										}
										type = EnumTypeList.get(componentId);
										if (type.valueType != 115) {
											throw new RuntimeException("C3410-1");
										}
										scriptIntValues[isp++] = type.method3086(chatTypedLowercase) ? 1 : 0;
										continue;
									}
									if (opcode == 3411) {
										isp--;
										componentId = scriptIntValues[isp];
										@Pc(3645) EnumType local3645 = EnumTypeList.get(componentId);
										scriptIntValues[isp++] = local3645.table.length();
										continue;
									}
								} else if (opcode < 3700) {
									if (opcode == 3600) {
										if (FriendList.state == 0) {
											scriptIntValues[isp++] = -2;
										} else if (FriendList.state == 1) {
											scriptIntValues[isp++] = -1;
										} else {
											scriptIntValues[isp++] = FriendList.friendCount;
										}
										continue;
									}
									if (opcode == 3601) {
										isp--;
										componentId = scriptIntValues[isp];
										if (FriendList.state == 2 && componentId < FriendList.friendCount) {
											scriptStringValues[ssp++] = FriendList.friendUsernames[componentId];
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == 3602) {
										isp--;
										componentId = scriptIntValues[isp];
										if (FriendList.state == 2 && FriendList.friendCount > componentId) {
											scriptIntValues[isp++] = FriendList.friendWorlds[componentId];
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == 3603) {
										isp--;
										componentId = scriptIntValues[isp];
										if (FriendList.state == 2 && FriendList.friendCount > componentId) {
											scriptIntValues[isp++] = FriendList.ranks[componentId];
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == 3604) {
										isp--;
										interfaceType = scriptIntValues[isp];
										ssp--;
										chatTyped = scriptStringValues[ssp];
										FriendList.setRank(chatTyped, interfaceType);
										continue;
									}
									if (opcode == 3605) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										FriendList.addFriend(chatTyped.encode37());
										continue;
									}
									if (opcode == 3606) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										FriendList.removeFriend(chatTyped.encode37());
										continue;
									}
									if (opcode == 3607) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										IgnoreList.addIgnore(chatTyped.encode37());
										continue;
									}
									if (opcode == 3608) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										IgnoreList.remove(chatTyped.encode37());
										continue;
									}
									if (opcode == 3609) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										if (chatTyped.startsWith(aClass100_446) || chatTyped.startsWith(aClass100_537)) {
											chatTyped = chatTyped.substring(7);
										}
										scriptIntValues[isp++] = FriendList.contains(chatTyped) ? 1 : 0;
										continue;
									}
									if (opcode == 3610) {
										isp--;
										componentId = scriptIntValues[isp];
										if (FriendList.state == 2 && FriendList.friendCount > componentId) {
											scriptStringValues[ssp++] = FriendList.worldNames[componentId];
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == 3611) {
										if (ClanChat.name == null) {
											scriptStringValues[ssp++] = EMPTY_STRING;
										} else {
											scriptStringValues[ssp++] = ClanChat.name.toTitleCase();
										}
										continue;
									}
									if (opcode == 3612) {
										if (ClanChat.name == null) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = ClanChat.size;
										}
										continue;
									}
									if (opcode == 3613) {
										isp--;
										componentId = scriptIntValues[isp];
										if (ClanChat.name != null && ClanChat.size > componentId) {
											scriptStringValues[ssp++] = ClanChat.members[componentId].username.toTitleCase();
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == 3614) {
										isp--;
										componentId = scriptIntValues[isp];
										if (ClanChat.name != null && componentId < ClanChat.size) {
											scriptIntValues[isp++] = ClanChat.members[componentId].world;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == 3615) {
										isp--;
										componentId = scriptIntValues[isp];
										if (ClanChat.name != null && ClanChat.size > componentId) {
											scriptIntValues[isp++] = ClanChat.members[componentId].rank;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == 3616) {
										scriptIntValues[isp++] = ClanChat.minKick;
										continue;
									}
									if (opcode == 3617) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										ClanChat.kick(chatTyped);
										continue;
									}
									if (opcode == 3618) {
										scriptIntValues[isp++] = ClanChat.rank;
										continue;
									}
									if (opcode == 3619) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										ClanChat.join(chatTyped.encode37());
										continue;
									}
									if (opcode == 3620) {
										ClanChat.leave();
										continue;
									}
									if (opcode == 3621) {
										if (FriendList.state == 0) {
											scriptIntValues[isp++] = -1;
										} else {
											scriptIntValues[isp++] = IgnoreList.ignoreCount;
										}
										continue;
									}
									if (opcode == 3622) {
										isp--;
										componentId = scriptIntValues[isp];
										if (FriendList.state != 0 && IgnoreList.ignoreCount > componentId) {
											scriptStringValues[ssp++] = Base37.fromBase37(IgnoreList.encodedIgnores[componentId]).toTitleCase();
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == 3623) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										if (chatTyped.startsWith(aClass100_446) || chatTyped.startsWith(aClass100_537)) {
											chatTyped = chatTyped.substring(7);
										}
										scriptIntValues[isp++] = IgnoreList.contains(chatTyped) ? 1 : 0;
										continue;
									}
									if (opcode == 3624) {
										isp--;
										componentId = scriptIntValues[isp];
										if (ClanChat.members != null && ClanChat.size > componentId && ClanChat.members[componentId].username.equalsIgnoreCase(PlayerList.self.username)) {
											scriptIntValues[isp++] = 1;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == 3625) {
										if (ClanChat.owner == null) {
											scriptStringValues[ssp++] = EMPTY_STRING;
										} else {
											scriptStringValues[ssp++] = ClanChat.owner.toTitleCase();
										}
										continue;
									}
									if (opcode == 3626) {
										isp--;
										componentId = scriptIntValues[isp];
										if (ClanChat.name != null && ClanChat.size > componentId) {
											scriptStringValues[ssp++] = ClanChat.members[componentId].worldName;
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == 3627) {
										isp--;
										componentId = scriptIntValues[isp];
										if (FriendList.state == 2 && componentId >= 0 && componentId < FriendList.friendCount) {
											scriptIntValues[isp++] = FriendList.friendGame[componentId] ? 1 : 0;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == 3628) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										if (chatTyped.startsWith(aClass100_446) || chatTyped.startsWith(aClass100_537)) {
											chatTyped = chatTyped.substring(7);
										}
										scriptIntValues[isp++] = FriendList.indexOf(chatTyped);
										continue;
									}
									if (opcode == 3629) {
										scriptIntValues[isp++] = Client.country;
										continue;
									}
								} else if (opcode < 4000) {
									if (opcode == 3903) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = StockMarketManager.offers[componentId].getType();
										continue;
									}
									if (opcode == 3904) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = StockMarketManager.offers[componentId].item;
										continue;
									}
									if (opcode == 3905) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = StockMarketManager.offers[componentId].price;
										continue;
									}
									if (opcode == 3906) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = StockMarketManager.offers[componentId].count;
										continue;
									}
									if (opcode == 3907) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = StockMarketManager.offers[componentId].completedCount;
										continue;
									}
									if (opcode == 3908) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = StockMarketManager.offers[componentId].completedGold;
										continue;
									}
									if (opcode == 3910) {
										isp--;
										componentId = scriptIntValues[isp];
										interfaceType = StockMarketManager.offers[componentId].getStatus();
										scriptIntValues[isp++] = interfaceType == 0 ? 1 : 0;
										continue;
									}
									if (opcode == 3911) {
										isp--;
										componentId = scriptIntValues[isp];
										interfaceType = StockMarketManager.offers[componentId].getStatus();
										scriptIntValues[isp++] = interfaceType == 2 ? 1 : 0;
										continue;
									}
									if (opcode == 3912) {
										isp--;
										componentId = scriptIntValues[isp];
										interfaceType = StockMarketManager.offers[componentId].getStatus();
										scriptIntValues[isp++] = interfaceType == 5 ? 1 : 0;
										continue;
									}
									if (opcode == 3913) {
										isp--;
										componentId = scriptIntValues[isp];
										interfaceType = StockMarketManager.offers[componentId].getStatus();
										scriptIntValues[isp++] = interfaceType == 1 ? 1 : 0;
										continue;
									}
								} else if (opcode < 4100) {
									if (opcode == 4000) {
										// add
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = interfaceType + componentId;
										continue;
									}
									if (opcode == 4001) {
										// sub
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = componentId - interfaceType;
										continue;
									}
									if (opcode == 4002) {
										// multiply
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = interfaceType * componentId;
										continue;
									}
									if (opcode == 4003) {
										// divide
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = componentId / interfaceType;
										continue;
									}
									if (opcode == 4004) {
										// random
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = (int) ((double) componentId * Math.random());
										continue;
									}
									if (opcode == 4005) {
										// randominc
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = (int) (Math.random() * (double) (componentId + 1));
										continue;
									}
									if (opcode == 4006) {
										// interpolate
										isp -= 5;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										i = scriptIntValues[isp + 3];
										j = scriptIntValues[isp + 2];
										start = scriptIntValues[isp + 4];
										scriptIntValues[isp++] = (interfaceType - componentId) * (start + -j) / (i - j) + componentId;
										continue;
									}
									@Pc(4899) long local4899;
									@Pc(4892) long local4892;
									if (opcode == 4007) {
										// addpercent
										isp -= 2;
										local4892 = scriptIntValues[isp];
										local4899 = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = (int) (local4892 * local4899 / 100L + local4892);
										continue;
									}
									if (opcode == 4008) {
										// setbit
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = componentId | 0x1 << interfaceType;
										continue;
									}
									if (opcode == 4009) {
										// clearbit
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = -(0x1 << interfaceType) - 1 & componentId;
										continue;
									}
									if (opcode == 4010) {
										// testbit
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = (componentId & 0x1 << interfaceType) == 0 ? 0 : 1;
										continue;
									}
									if (opcode == 4011) {
										// modulo
										isp -= 2;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = componentId % interfaceType;
										continue;
									}
									if (opcode == 4012) {
										// pow
										isp -= 2;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										if (componentId == 0) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = (int) Math.pow((double) componentId, (double) interfaceType);
										}
										continue;
									}
									if (opcode == 4013) {
										// invpow
										isp -= 2;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										if (componentId == 0) {
											scriptIntValues[isp++] = 0;
										} else if (interfaceType == 0) {
											scriptIntValues[isp++] = Integer.MAX_VALUE;
										} else {
											scriptIntValues[isp++] = (int) Math.pow((double) componentId, 1.0D / (double) interfaceType);
										}
										continue;
									}
									if (opcode == 4014) {
										isp -= 2;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = interfaceType & componentId;
										continue;
									}
									if (opcode == 4015) {
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = componentId | interfaceType;
										continue;
									}
									if (opcode == 4016) {
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = componentId < interfaceType ? componentId : interfaceType;
										continue;
									}
									if (opcode == 4017) {
										isp -= 2;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = interfaceType >= componentId ? interfaceType : componentId;
										continue;
									}
									if (opcode == 4018) {
										isp -= 3;
										local4892 = scriptIntValues[isp];
										local4899 = scriptIntValues[isp + 1];
										@Pc(5251) long local5251 = (long) scriptIntValues[isp + 2];
										scriptIntValues[isp++] = (int) (local4892 * local5251 / local4899);
										continue;
									}
								} else if (opcode >= 4200) {
									@Pc(5294) ParamType local5294;
									if (opcode < 4300) {
										if (opcode == 4200) {
											isp--;
											componentId = scriptIntValues[isp];
											scriptStringValues[ssp++] = ObjTypeList.get(componentId).name;
											continue;
										}
										@Pc(11269) ObjType local11269;
										if (opcode == 4201) {
											isp -= 2;
											componentId = scriptIntValues[isp];
											interfaceType = scriptIntValues[isp + 1];
											local11269 = ObjTypeList.get(componentId);
											if (interfaceType >= 1 && interfaceType <= 5 && local11269.op[interfaceType - 1] != null) {
												scriptStringValues[ssp++] = local11269.op[interfaceType - 1];
												continue;
											}
											scriptStringValues[ssp++] = EMPTY_STRING;
											continue;
										}
										if (opcode == 4202) {
											isp -= 2;
											componentId = scriptIntValues[isp];
											interfaceType = scriptIntValues[isp + 1];
											local11269 = ObjTypeList.get(componentId);
											if (interfaceType >= 1 && interfaceType <= 5 && local11269.iop[interfaceType - 1] != null) {
												scriptStringValues[ssp++] = local11269.iop[interfaceType - 1];
												continue;
											}
											scriptStringValues[ssp++] = EMPTY_STRING;
											continue;
										}
										if (opcode == 4203) {
											isp--;
											componentId = scriptIntValues[isp];
											scriptIntValues[isp++] = ObjTypeList.get(componentId).cost;
											continue;
										}
										if (opcode == 4204) {
											isp--;
											componentId = scriptIntValues[isp];
											scriptIntValues[isp++] = ObjTypeList.get(componentId).stackable == 1 ? 1 : 0;
											continue;
										}
										@Pc(11417) ObjType local11417;
										if (opcode == 4205) {
											isp--;
											componentId = scriptIntValues[isp];
											local11417 = ObjTypeList.get(componentId);
											if (local11417.certTemplate == -1 && local11417.certLink >= 0) {
												scriptIntValues[isp++] = local11417.certLink;
												continue;
											}
											scriptIntValues[isp++] = componentId;
											continue;
										}
										if (opcode == 4206) {
											isp--;
											componentId = scriptIntValues[isp];
											local11417 = ObjTypeList.get(componentId);
											if (local11417.certTemplate >= 0 && local11417.certLink >= 0) {
												scriptIntValues[isp++] = local11417.certLink;
												continue;
											}
											scriptIntValues[isp++] = componentId;
											continue;
										}
										if (opcode == 4207) {
											isp--;
											componentId = scriptIntValues[isp];
											scriptIntValues[isp++] = ObjTypeList.get(componentId).members ? 1 : 0;
											continue;
										}
										if (opcode == 4208) {
											isp -= 2;
											componentId = scriptIntValues[isp];
											interfaceType = scriptIntValues[isp + 1];
											local5294 = ParamTypeList.get(interfaceType);
											if (local5294.isString()) {
												scriptStringValues[ssp++] = ObjTypeList.get(componentId).getParam(local5294.defaultString, interfaceType);
											} else {
												scriptIntValues[isp++] = ObjTypeList.get(componentId).getParam(local5294.defaultInt, interfaceType);
											}
											continue;
										}
										if (opcode == 4210) {
											ssp--;
											chatTyped = scriptStringValues[ssp];
											isp--;
											interfaceType = scriptIntValues[isp];
											Find.search(interfaceType == 1, chatTyped);
											scriptIntValues[isp++] = Find.index;
											continue;
										}
										if (opcode == 4211) {
											if (Find.results != null && Find.size < Find.index) {
												scriptIntValues[isp++] = Find.results[Find.size++] & 0xFFFF;
												continue;
											}
											scriptIntValues[isp++] = -1;
											continue;
										}
										if (opcode == 4212) {
											Find.size = 0;
											continue;
										}
									} else if (opcode < 4400) {
										if (opcode == 4300) {
											isp -= 2;
											componentId = scriptIntValues[isp];
											interfaceType = scriptIntValues[isp + 1];
											local5294 = ParamTypeList.get(interfaceType);
											if (local5294.isString()) {
												scriptStringValues[ssp++] = NpcTypeList.get(componentId).getParam(interfaceType, local5294.defaultString);
											} else {
												scriptIntValues[isp++] = NpcTypeList.get(componentId).getParam(interfaceType, local5294.defaultInt);
											}
											continue;
										}
									} else if (opcode >= 4500) {
										if (opcode >= 4600) {
											if (opcode < 5100) {
												if (opcode == 5000) {
													scriptIntValues[isp++] = Chat.publicFilter;
													continue;
												}
												if (opcode == 5001) {
													isp -= 3;
													Chat.publicFilter = scriptIntValues[isp];
													Chat.privateFilter = scriptIntValues[isp + 1];
													Chat.tradeFilter = scriptIntValues[isp + 2];
													Protocol.outboundBuffer.pIsaac1(157);
													Protocol.outboundBuffer.p1(Chat.publicFilter);
													Protocol.outboundBuffer.p1(Chat.privateFilter);
													Protocol.outboundBuffer.p1(Chat.tradeFilter);
													continue;
												}
												if (opcode == 5002) {
													ssp--;
													chatTyped = scriptStringValues[ssp];
													isp -= 2;
													interfaceType = scriptIntValues[isp];
													j = scriptIntValues[isp + 1];
													Protocol.outboundBuffer.pIsaac1(REPORT_ABUSE);
													Protocol.outboundBuffer.p8(chatTyped.encode37());
													Protocol.outboundBuffer.p1(interfaceType - 1);
													Protocol.outboundBuffer.p1(j);
													continue;
												}
												if (opcode == 5003) {
													chatTypedLowercase = null;
													isp--;
													componentId = scriptIntValues[isp];
													if (componentId < 100) {
														chatTypedLowercase = Chat.messages[componentId];
													}
													if (chatTypedLowercase == null) {
														chatTypedLowercase = EMPTY_STRING;
													}
													scriptStringValues[ssp++] = chatTypedLowercase;
													continue;
												}
												if (opcode == 5004) {
													isp--;
													componentId = scriptIntValues[isp];
													interfaceType = -1;
													if (componentId < 100 && Chat.messages[componentId] != null) {
														interfaceType = Chat.types[componentId];
													}
													scriptIntValues[isp++] = interfaceType;
													continue;
												}
												if (opcode == 5005) {
													scriptIntValues[isp++] = Chat.privateFilter;
													continue;
												}
												if (opcode == 5008) {
													ssp--;
													chatTyped = scriptStringValues[ssp];
													if (!chatTyped.startsWith(aClass100_74)) {
														if (LoginManager.staffModLevel == 0 && (LoginManager.playerUnderage && !LoginManager.parentalChatConsent || LoginManager.worldQuickChat)) {
															continue;
														}
														chatTypedLowercase = chatTyped.toLowerCase();
														@Pc(5555) byte color = 0;
														if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL0)) {
															color = 0;
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL0.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL1)) {
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL1.length());
															color = 1;
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL2)) {
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL2.length());
															color = 2;
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL3)) {
															color = 3;
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL3.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL4)) {
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL4.length());
															color = 4;
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL5)) {
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL5.length());
															color = 5;
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL6)) {
															color = 6;
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL6.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL7)) {
															color = 7;
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL7.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL8)) {
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL8.length());
															color = 8;
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL9)) {
															color = 9;
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL9.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL10)) {
															color = 10;
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL10.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL11)) {
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL11.length());
															color = 11;
														} else if (Client.language != 0) {
															if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL0)) {
																color = 0;
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL0.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL1)) {
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL1.length());
																color = 1;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL2)) {
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL2.length());
																color = 2;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL3)) {
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL3.length());
																color = 3;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL4)) {
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL4.length());
																color = 4;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL5)) {
																color = 5;
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL5.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL6)) {
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL6.length());
																color = 6;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL7)) {
																color = 7;
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL7.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL8)) {
																color = 8;
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL8.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL9)) {
																color = 9;
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL9.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL10)) {
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL10.length());
																color = 10;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL11)) {
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL11.length());
																color = 11;
															}
														}
														@Pc(5943) byte effect = 0;
														chatTypedLowercase = chatTyped.toLowerCase();
														if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATEFFECT1)) {
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATEFFECT1.length());
															effect = 1;
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATEFFECT2)) {
															effect = 2;
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATEFFECT2.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATEFFECT3)) {
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATEFFECT3.length());
															effect = 3;
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATEFFECT4)) {
															effect = 4;
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATEFFECT4.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATEFFECTC5)) {
															effect = 5;
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATEFFECTC5.length());
														} else if (Client.language != 0) {
															if (chatTypedLowercase.startsWith(LocalizedText.CHATEFFECT1)) {
																chatTyped = chatTyped.substring(LocalizedText.CHATEFFECT1.length());
																effect = 1;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATEFFECT2)) {
																effect = 2;
																chatTyped = chatTyped.substring(LocalizedText.CHATEFFECT2.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATEFFECT3)) {
																effect = 3;
																chatTyped = chatTyped.substring(LocalizedText.CHATEFFECT3.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATEFFECT4)) {
																effect = 4;
																chatTyped = chatTyped.substring(LocalizedText.CHATEFFECT4.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATEFFECT5)) {
																chatTyped = chatTyped.substring(LocalizedText.CHATEFFECT5.length());
																effect = 5;
															}
														}
														Protocol.outboundBuffer.pIsaac1(MESSAGE_PUBLIC);
														Protocol.outboundBuffer.p1(0);
														start = Protocol.outboundBuffer.offset;
														Protocol.outboundBuffer.p1(color);
														Protocol.outboundBuffer.p1(effect);
														WordPack.writeString(Protocol.outboundBuffer, chatTyped);
														Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - start);
														continue;
													}
													Cheat.execute(chatTyped);
													continue;
												}
												if (opcode == 5009) {
													ssp -= 2;
													chatTypedLowercase = scriptStringValues[ssp + 1];
													chatTyped = scriptStringValues[ssp];
													if (LoginManager.staffModLevel != 0 || (!LoginManager.playerUnderage || LoginManager.parentalChatConsent) && !LoginManager.worldQuickChat) {
														Protocol.outboundBuffer.pIsaac1(MESSAGE_PRIVATE);
														Protocol.outboundBuffer.p1(0);
														j = Protocol.outboundBuffer.offset;
														Protocol.outboundBuffer.p8(chatTyped.encode37());
														WordPack.writeString(Protocol.outboundBuffer, chatTypedLowercase);
														Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - j);
													}
													continue;
												}
												if (opcode == 5010) {
													isp--;
													componentId = scriptIntValues[isp];
													chatTypedLowercase = null;
													if (componentId < 100) {
														chatTypedLowercase = Chat.names[componentId];
													}
													if (chatTypedLowercase == null) {
														chatTypedLowercase = EMPTY_STRING;
													}
													scriptStringValues[ssp++] = chatTypedLowercase;
													continue;
												}
												if (opcode == 5011) {
													isp--;
													componentId = scriptIntValues[isp];
													chatTypedLowercase = null;
													if (componentId < 100) {
														chatTypedLowercase = Chat.clans[componentId];
													}
													if (chatTypedLowercase == null) {
														chatTypedLowercase = EMPTY_STRING;
													}
													scriptStringValues[ssp++] = chatTypedLowercase;
													continue;
												}
												if (opcode == 5012) {
													isp--;
													componentId = scriptIntValues[isp];
													interfaceType = -1;
													if (componentId < 100) {
														interfaceType = Chat.phraseIds[componentId];
													}
													scriptIntValues[isp++] = interfaceType;
													continue;
												}
												if (opcode == 5015) {
													if (PlayerList.self == null || PlayerList.self.username == null) {
														chatTyped = Player.usernameInput;
													} else {
														chatTyped = PlayerList.self.getUsername();
													}
													scriptStringValues[ssp++] = chatTyped;
													continue;
												}
												if (opcode == 5016) {
													scriptIntValues[isp++] = Chat.tradeFilter;
													continue;
												}
												if (opcode == 5017) {
													scriptIntValues[isp++] = Chat.size;
													continue;
												}
												if (opcode == 5050) {
													isp--;
													componentId = scriptIntValues[isp];
													scriptStringValues[ssp++] = QuickChatCatTypeList.get(componentId).description;
													continue;
												}
												@Pc(6378) QuickChatCatType local6378;
												if (opcode == 5051) {
													isp--;
													componentId = scriptIntValues[isp];
													local6378 = QuickChatCatTypeList.get(componentId);
													if (local6378.subcategories == null) {
														scriptIntValues[isp++] = 0;
													} else {
														scriptIntValues[isp++] = local6378.subcategories.length;
													}
													continue;
												}
												if (opcode == 5052) {
													isp -= 2;
													componentId = scriptIntValues[isp];
													interfaceType = scriptIntValues[isp + 1];
													@Pc(6416) QuickChatCatType local6416 = QuickChatCatTypeList.get(componentId);
													i = local6416.subcategories[interfaceType];
													scriptIntValues[isp++] = i;
													continue;
												}
												if (opcode == 5053) {
													isp--;
													componentId = scriptIntValues[isp];
													local6378 = QuickChatCatTypeList.get(componentId);
													if (local6378.phrases == null) {
														scriptIntValues[isp++] = 0;
													} else {
														scriptIntValues[isp++] = local6378.phrases.length;
													}
													continue;
												}
												if (opcode == 5054) {
													isp -= 2;
													interfaceType = scriptIntValues[isp + 1];
													componentId = scriptIntValues[isp];
													scriptIntValues[isp++] = QuickChatCatTypeList.get(componentId).phrases[interfaceType];
													continue;
												}
												if (opcode == 5055) {
													isp--;
													componentId = scriptIntValues[isp];
													scriptStringValues[ssp++] = QuickChatPhraseTypeList.get(componentId).getText();
													continue;
												}
												if (opcode == 5056) {
													isp--;
													componentId = scriptIntValues[isp];
													@Pc(6527) QuickChatPhraseType local6527 = QuickChatPhraseTypeList.get(componentId);
													if (local6527.autoResponses == null) {
														scriptIntValues[isp++] = 0;
													} else {
														scriptIntValues[isp++] = local6527.autoResponses.length;
													}
													continue;
												}
												if (opcode == 5057) {
													isp -= 2;
													interfaceType = scriptIntValues[isp + 1];
													componentId = scriptIntValues[isp];
													scriptIntValues[isp++] = QuickChatPhraseTypeList.get(componentId).autoResponses[interfaceType];
													continue;
												}
												if (opcode == 5058) {
													activePhrase = new QuickChatPhrase();
													isp--;
													activePhrase.id = scriptIntValues[isp];
													activePhrase.type = QuickChatPhraseTypeList.get(activePhrase.id);
													activePhrase.values = new int[activePhrase.type.getDynamicCommandCount()];
													continue;
												}
												if (opcode == 5059) {
													Protocol.outboundBuffer.pIsaac1(167);
													Protocol.outboundBuffer.p1(0);
													componentId = Protocol.outboundBuffer.offset;
													Protocol.outboundBuffer.p1(0);
													Protocol.outboundBuffer.p2(activePhrase.id);
													activePhrase.type.encodeMessage(Protocol.outboundBuffer, activePhrase.values);
													Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - componentId);
													continue;
												}
												if (opcode == 5060) {
													ssp--;
													chatTyped = scriptStringValues[ssp];
													Protocol.outboundBuffer.pIsaac1(178);
													Protocol.outboundBuffer.p1(0);
													interfaceType = Protocol.outboundBuffer.offset;
													Protocol.outboundBuffer.p8(chatTyped.encode37());
													Protocol.outboundBuffer.p2(activePhrase.id);
													activePhrase.type.encodeMessage(Protocol.outboundBuffer, activePhrase.values);
													Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - interfaceType);
													continue;
												}
												if (opcode == 5061) {
													Protocol.outboundBuffer.pIsaac1(167);
													Protocol.outboundBuffer.p1(0);
													componentId = Protocol.outboundBuffer.offset;
													Protocol.outboundBuffer.p1(1);
													Protocol.outboundBuffer.p2(activePhrase.id);
													activePhrase.type.encodeMessage(Protocol.outboundBuffer, activePhrase.values);
													Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - componentId);
													continue;
												}
												if (opcode == 5062) {
													isp -= 2;
													interfaceType = scriptIntValues[isp + 1];
													componentId = scriptIntValues[isp];
													scriptIntValues[isp++] = QuickChatCatTypeList.get(componentId).subcategoryShortcuts[interfaceType];
													continue;
												}
												if (opcode == 5063) {
													isp -= 2;
													interfaceType = scriptIntValues[isp + 1];
													componentId = scriptIntValues[isp];
													scriptIntValues[isp++] = QuickChatCatTypeList.get(componentId).phraseShortcuts[interfaceType];
													continue;
												}
												if (opcode == 5064) {
													isp -= 2;
													interfaceType = scriptIntValues[isp + 1];
													componentId = scriptIntValues[isp];
													if (interfaceType == -1) {
														scriptIntValues[isp++] = -1;
													} else {
														scriptIntValues[isp++] = QuickChatCatTypeList.get(componentId).method469(interfaceType);
													}
													continue;
												}
												if (opcode == 5065) {
													isp -= 2;
													componentId = scriptIntValues[isp];
													interfaceType = scriptIntValues[isp + 1];
													if (interfaceType == -1) {
														scriptIntValues[isp++] = -1;
													} else {
														scriptIntValues[isp++] = QuickChatCatTypeList.get(componentId).method466(interfaceType);
													}
													continue;
												}
												if (opcode == 5066) {
													isp--;
													componentId = scriptIntValues[isp];
													scriptIntValues[isp++] = QuickChatPhraseTypeList.get(componentId).getDynamicCommandCount();
													continue;
												}
												if (opcode == 5067) {
													isp -= 2;
													interfaceType = scriptIntValues[isp + 1];
													componentId = scriptIntValues[isp];
													j = QuickChatPhraseTypeList.get(componentId).getDynamicCommand(interfaceType);
													scriptIntValues[isp++] = j;
													continue;
												}
												if (opcode == 5068) {
													isp -= 2;
													componentId = scriptIntValues[isp];
													interfaceType = scriptIntValues[isp + 1];
													activePhrase.values[componentId] = interfaceType;
													continue;
												}
												if (opcode == 5069) {
													isp -= 2;
													componentId = scriptIntValues[isp];
													interfaceType = scriptIntValues[isp + 1];
													activePhrase.values[componentId] = interfaceType;
													continue;
												}
												if (opcode == 5070) {
													isp -= 3;
													componentId = scriptIntValues[isp];
													j = scriptIntValues[isp + 2];
													interfaceType = scriptIntValues[isp + 1];
													@Pc(6996) QuickChatPhraseType local6996 = QuickChatPhraseTypeList.get(componentId);
													if (local6996.getDynamicCommand(interfaceType) != 0) {
														throw new RuntimeException("bad command");
													}
													scriptIntValues[isp++] = local6996.getDynamicCommandParam(j, interfaceType);
													continue;
												}
												if (opcode == 5071) {
													ssp--;
													chatTyped = scriptStringValues[ssp];
													isp--;
													local1552 = scriptIntValues[isp] == 1;
													Find.findQuickChatPhrases(local1552, chatTyped);
													scriptIntValues[isp++] = Find.index;
													continue;
												}
												if (opcode == 5072) {
													if (Find.results != null && Find.size < Find.index) {
														scriptIntValues[isp++] = Find.results[Find.size++] & 0xFFFF;
														continue;
													}
													scriptIntValues[isp++] = -1;
													continue;
												}
												if (opcode == 5073) {
													Find.size = 0;
													continue;
												}
											} else if (opcode < 5200) {
												if (opcode == 5100) {
													if (Keyboard.pressedKeys[86]) {
														scriptIntValues[isp++] = 1;
													} else {
														scriptIntValues[isp++] = 0;
													}
													continue;
												}
												if (opcode == 5101) {
													if (Keyboard.pressedKeys[82]) {
														scriptIntValues[isp++] = 1;
													} else {
														scriptIntValues[isp++] = 0;
													}
													continue;
												}
												if (opcode == 5102) {
													if (Keyboard.pressedKeys[81]) {
														scriptIntValues[isp++] = 1;
													} else {
														scriptIntValues[isp++] = 0;
													}
													continue;
												}
											} else {
												@Pc(7566) boolean local7566;
												if (opcode < 5300) {
													if (opcode == 5200) {
														isp--;
														WorldMap.setTargetZoom(scriptIntValues[isp]);
														continue;
													}
													if (opcode == 5201) {
														scriptIntValues[isp++] = WorldMap.getTargetZoom();
														continue;
													}
													if (opcode == 5202) {
														isp--;
														WorldMap.method4444(scriptIntValues[isp]);
														continue;
													}
													if (opcode == 5203) {
														ssp--;
														WorldMap.method4656(scriptStringValues[ssp]);
														continue;
													}
													if (opcode == 5204) {
														scriptStringValues[ssp - 1] = WorldMap.method923(scriptStringValues[ssp - 1]);
														continue;
													}
													if (opcode == 5205) {
														ssp--;
														WorldMap.method1853(scriptStringValues[ssp]);
														continue;
													}
													if (opcode == 5206) {
														isp--;
														componentId = scriptIntValues[isp];
														@Pc(7264) Map local7264 = MapList.getContainingSource(componentId >> 14 & 0x3FFF, componentId & 0x3FFF);
														if (local7264 == null) {
															scriptStringValues[ssp++] = EMPTY_STRING;
														} else {
															scriptStringValues[ssp++] = local7264.group;
														}
														continue;
													}
													@Pc(7293) Map local7293;
													if (opcode == 5207) {
														ssp--;
														local7293 = MapList.get(scriptStringValues[ssp]);
														if (local7293 != null && local7293.name != null) {
															scriptStringValues[ssp++] = local7293.name;
															continue;
														}
														scriptStringValues[ssp++] = EMPTY_STRING;
														continue;
													}
													if (opcode == 5208) {
														scriptIntValues[isp++] = WorldMap.anInt2387;
														scriptIntValues[isp++] = WorldMap.anInt1176;
														continue;
													}
													if (opcode == 5209) {
														scriptIntValues[isp++] = WorldMap.originX + WorldMap.anInt435;
														scriptIntValues[isp++] = WorldMap.originZ + WorldMap.length - WorldMap.anInt919 - 1;
														continue;
													}
													if (opcode == 5210) {
														local7293 = WorldMap.getCurrentMap();
														if (local7293 == null) {
															scriptIntValues[isp++] = 0;
															scriptIntValues[isp++] = 0;
														} else {
															scriptIntValues[isp++] = local7293.originX * 64;
															scriptIntValues[isp++] = local7293.originZ * 64;
														}
														continue;
													}
													if (opcode == 5211) {
														local7293 = WorldMap.getCurrentMap();
														if (local7293 == null) {
															scriptIntValues[isp++] = 0;
															scriptIntValues[isp++] = 0;
														} else {
															scriptIntValues[isp++] = local7293.displayMaxZ - local7293.displayMinX;
															scriptIntValues[isp++] = local7293.displayMinZ - local7293.displayMaxX;
														}
														continue;
													}
													if (opcode == 5212) {
														componentId = WorldMap.method2352();
														j = 0;
														if (componentId == -1) {
															chatTypedLowercase = EMPTY_STRING;
														} else {
															chatTypedLowercase = WorldMap.labels.text[componentId];
															j = WorldMap.labels.getLowerTwoBits(componentId);
														}
														chatTypedLowercase = chatTypedLowercase.method3140(aClass100_639, aClass100_10);
														scriptStringValues[ssp++] = chatTypedLowercase;
														scriptIntValues[isp++] = j;
														continue;
													}
													if (opcode == 5213) {
														j = 0;
														componentId = WorldMap.method2385();
														if (componentId == -1) {
															chatTypedLowercase = EMPTY_STRING;
														} else {
															chatTypedLowercase = WorldMap.labels.text[componentId];
															j = WorldMap.labels.getLowerTwoBits(componentId);
														}
														chatTypedLowercase = chatTypedLowercase.method3140(aClass100_639, aClass100_10);
														scriptStringValues[ssp++] = chatTypedLowercase;
														scriptIntValues[isp++] = j;
														continue;
													}
													if (opcode == 5214) {
														isp--;
														componentId = scriptIntValues[isp];
														WorldMap.method3616(componentId >> 14 & 0x3FFF, componentId & 0x3FFF);
														continue;
													}
													if (opcode == 5215) {
														isp--;
														componentId = scriptIntValues[isp];
														ssp--;
														chatTypedLowercase = scriptStringValues[ssp];
														local7566 = false;
														@Pc(7577) SecondaryLinkedList local7577 = method3333(componentId >> 14 & 0x3FFF, componentId & 0x3FFF);
														for (@Pc(7582) Map local7582 = (Map) local7577.head(); local7582 != null; local7582 = (Map) local7577.next()) {
															if (local7582.group.equalsIgnoreCase(chatTypedLowercase)) {
																local7566 = true;
																break;
															}
														}
														if (local7566) {
															scriptIntValues[isp++] = 1;
														} else {
															scriptIntValues[isp++] = 0;
														}
														continue;
													}
													if (opcode == 5216) {
														isp--;
														componentId = scriptIntValues[isp];
														MapList.method4332(componentId);
														continue;
													}
													if (opcode == 5217) {
														isp--;
														componentId = scriptIntValues[isp];
														if (MapList.method1855(componentId)) {
															scriptIntValues[isp++] = 1;
														} else {
															scriptIntValues[isp++] = 0;
														}
														continue;
													}
													if (opcode == 5218) {
														local7293 = WorldMap.getCurrentMap();
														if (local7293 == null) {
															scriptIntValues[isp++] = -1;
														} else {
															scriptIntValues[isp++] = local7293.defaultZoom;
														}
														continue;
													}
													if (opcode == 5219) {
														ssp--;
														WorldMap.method1149(scriptStringValues[ssp]);
														continue;
													}
													if (opcode == 5220) {
														scriptIntValues[isp++] = WorldMap.loadPercentage == 100 ? 1 : 0;
														continue;
													}
												} else if (opcode < 5400) {
													if (opcode == 5300) {
														isp -= 2;
														interfaceType = scriptIntValues[isp + 1];
														componentId = scriptIntValues[isp];
														DisplayMode.setWindowMode(false, 3, componentId, interfaceType);
														scriptIntValues[isp++] = GameShell.fullScreenFrame == null ? 0 : 1;
														continue;
													}
													if (opcode == 5301) {
														if (GameShell.fullScreenFrame != null) {
															DisplayMode.setWindowMode(false, Preferences.favoriteWorlds, -1, -1);
														}
														continue;
													}
													if (opcode == 5302) {
														@Pc(7780) DisplayMode[] local7780 = DisplayMode.getModes();
														scriptIntValues[isp++] = local7780.length;
														continue;
													}
													if (opcode == 5303) {
														isp--;
														componentId = scriptIntValues[isp];
														@Pc(7800) DisplayMode[] local7800 = DisplayMode.getModes();
														scriptIntValues[isp++] = local7800[componentId].width;
														scriptIntValues[isp++] = local7800[componentId].height;
														continue;
													}
													if (opcode == 5305) {
														interfaceType = Preferences.fullScreenHeight;
														componentId = Preferences.fullScreenWidth;
														j = -1;
														@Pc(7833) DisplayMode[] local7833 = DisplayMode.getModes();
														for (start = 0; start < local7833.length; start++) {
															@Pc(7843) DisplayMode local7843 = local7833[start];
															if (componentId == local7843.width && local7843.height == interfaceType) {
																j = start;
																break;
															}
														}
														scriptIntValues[isp++] = j;
														continue;
													}
													if (opcode == 5306) {
														scriptIntValues[isp++] = DisplayMode.getWindowMode();
														continue;
													}
													if (opcode == 5307) {
														isp--;
														componentId = scriptIntValues[isp];
														if (componentId < 0 || componentId > 2) {
															componentId = 0;
														}
														DisplayMode.setWindowMode(false, componentId, -1, -1);
														continue;
													}
													if (opcode == 5308) {
														scriptIntValues[isp++] = Preferences.favoriteWorlds;
														continue;
													}
													if (opcode == 5309) {
														isp--;
														componentId = scriptIntValues[isp];
														if (componentId < 0 || componentId > 2) {
															componentId = 0;
														}
														Preferences.favoriteWorlds = componentId;
														Preferences.write(GameShell.signLink);
														continue;
													}
												} else if (opcode < 5500) {
													if (opcode == 5400) {
														ssp -= 2;
														chatTyped = scriptStringValues[ssp];
														chatTypedLowercase = scriptStringValues[ssp + 1];
														isp--;
														j = scriptIntValues[isp];
														Protocol.outboundBuffer.pIsaac1(117);
														Protocol.outboundBuffer.p1(Packet.gjstrlen(chatTyped) + Packet.gjstrlen(chatTypedLowercase) + 1);
														Protocol.outboundBuffer.pjstr(chatTyped);
														Protocol.outboundBuffer.pjstr(chatTypedLowercase);
														Protocol.outboundBuffer.p1(j);
														continue;
													}
													if (opcode == 5401) {
														isp -= 2;
														Client.aShortArray88[scriptIntValues[isp]] = (short) ColorUtils.rgbToHsl(scriptIntValues[isp + 1]);
														ObjTypeList.clearModels();
														ObjTypeList.clearSprites();
														NpcTypeList.method4649();
														NpcTypeList.method443();
														method1807();
														continue;
													}
													if (opcode == 5405) {
														isp -= 2;
														componentId = scriptIntValues[isp];
														interfaceType = scriptIntValues[isp + 1];
														if (componentId >= 0 && componentId < 2) {
															Camera.cameraSplines[componentId] = new int[interfaceType << 1][4];
														}
														continue;
													}
													if (opcode == 5406) {
														isp -= 7;
														componentId = scriptIntValues[isp];
														interfaceType = scriptIntValues[isp + 1] << 1;
														i = scriptIntValues[isp + 3];
														j = scriptIntValues[isp + 2];
														start = scriptIntValues[isp + 4];
														@Pc(8108) int local8108 = scriptIntValues[isp + 6];
														childId = scriptIntValues[isp + 5];
														if (componentId >= 0 && componentId < 2 && Camera.cameraSplines[componentId] != null && interfaceType >= 0 && Camera.cameraSplines[componentId].length > interfaceType) {
															Camera.cameraSplines[componentId][interfaceType] = new int[] { (j >> 14 & 0x3FFF) * 128, i, (j & 0x3FFF) * 128, local8108 };
															Camera.cameraSplines[componentId][interfaceType + 1] = new int[] { (start >> 14 & 0x3FFF) * 128, childId, (start & 0x3FFF) * 128 };
														}
														continue;
													}
													if (opcode == 5407) {
														isp--;
														componentId = Camera.cameraSplines[scriptIntValues[isp]].length >> 1;
														scriptIntValues[isp++] = componentId;
														continue;
													}
													if (opcode == 5411) {
														if (GameShell.fullScreenFrame != null) {
															DisplayMode.setWindowMode(false, Preferences.favoriteWorlds, -1, -1);
														}
														if (GameShell.frame == null) {
															openUrl(buildConfigUrl(), false);
														} else {
															System.exit(0);
														}
														continue;
													}
													if (opcode == 5419) {
														chatTyped = EMPTY_STRING;
														if (Player.lastLogAddress != null) {
															chatTyped = JString.formatIp(Player.lastLogAddress.intArg2);
															try {
																if (Player.lastLogAddress.result != null) {
																	@Pc(8281) byte[] local8281 = ((String) Player.lastLogAddress.result).getBytes("ISO-8859-1");
																	chatTyped = JString.decodeString(local8281, local8281.length, 0);
																}
															} catch (@Pc(8290) UnsupportedEncodingException ignored) {
															}
														}
														scriptStringValues[ssp++] = chatTyped;
														continue;
													}
													if (opcode == 5420) {
														scriptIntValues[isp++] = SignLink.anInt5928 == 3 ? 1 : 0;
														continue;
													}
													if (opcode == 5421) {
														if (GameShell.fullScreenFrame != null) {
															DisplayMode.setWindowMode(false, Preferences.favoriteWorlds, -1, -1);
														}
														isp--;
														local1552 = scriptIntValues[isp] == 1;
														ssp--;
														chatTyped = scriptStringValues[ssp];
														@Pc(8356) JString local8356 = JString.concatenate(new JString[] { buildConfigUrl(), chatTyped });
														if (GameShell.frame != null || local1552 && SignLink.anInt5928 != 3 && SignLink.osName.startsWith("win") && !Client.haveIe6) {
															Protocol.newTab = local1552;
															url = local8356;
															Protocol.openUrlRequest = GameShell.signLink.openUrl(new String(local8356.method3148(), "ISO-8859-1"));
															continue;
														}
														openUrl(local8356, local1552);
														continue;
													}
													if (opcode == 5422) {
														isp--;
														j = scriptIntValues[isp];
														ssp -= 2;
														chatTypedLowercase = scriptStringValues[ssp + 1];
														chatTyped = scriptStringValues[ssp];
														if (chatTyped.length() > 0) {
															if (PlayerList.playerNames == null) {
																PlayerList.playerNames = new JString[PlayerList.anIntArray309[Client.game]];
															}
															PlayerList.playerNames[j] = chatTyped;
														}
														if (chatTypedLowercase.length() > 0) {
															if (PlayerList.playerNames2 == null) {
																PlayerList.playerNames2 = new JString[PlayerList.anIntArray309[Client.game]];
															}
															PlayerList.playerNames2[j] = chatTypedLowercase;
														}
														continue;
													}
													if (opcode == 5423) {
														ssp--;
														scriptStringValues[ssp].printToConsole();
														continue;
													}
													if (opcode == 5424) {
														isp -= 11;
														LoginManager.anInt1275 = scriptIntValues[isp];
														LoginManager.anInt2910 = scriptIntValues[isp + 1];
														LoginManager.anInt5457 = scriptIntValues[isp + 2];
														LoginManager.anInt5208 = scriptIntValues[isp + 3];
														LoginManager.anInt1736 = scriptIntValues[isp + 4];
														LoginManager.anInt4073 = scriptIntValues[isp + 5];
														LoginManager.anInt2261 = scriptIntValues[isp + 6];
														LoginManager.anInt3324 = scriptIntValues[isp + 7];
														LoginManager.anInt5556 = scriptIntValues[isp + 8];
														LoginManager.anInt4581 = scriptIntValues[isp + 9];
														LoginManager.anInt5752 = scriptIntValues[isp + 10];
														Client.js5Archive8.isFileReady(LoginManager.anInt1736);
														Client.js5Archive8.isFileReady(LoginManager.anInt4073);
														Client.js5Archive8.isFileReady(LoginManager.anInt2261);
														Client.js5Archive8.isFileReady(LoginManager.anInt3324);
														Client.js5Archive8.isFileReady(LoginManager.anInt5556);
														ComponentList.hasScrollbar = true;
														continue;
													}
													if (opcode == 5425) {
														LoginManager.method4637();
														ComponentList.hasScrollbar = false;
														continue;
													}
													if (opcode == 5426) {
														isp--;
														anInt5794 = scriptIntValues[isp];
														continue;
													}
													if (opcode == 5427) {
														isp -= 2;
														MiniMap.anInt4075 = scriptIntValues[isp];
														MiniMap.anInt5073 = scriptIntValues[isp + 1];
														continue;
													}
												} else if (opcode < 5600) {
													if (opcode == 5500) {
														isp -= 4;
														componentId = scriptIntValues[isp];
														i = scriptIntValues[isp + 3];
														j = scriptIntValues[isp + 2];
														interfaceType = scriptIntValues[isp + 1];
														Camera.setCameraTargetPosition(false, j, interfaceType, i, (componentId & 0x3FFF) - Camera.sceneBaseTileZ, (componentId >> 14 & 0x3FFF) - Camera.sceneBaseTileX);
														continue;
													}
													if (opcode == 5501) {
														isp -= 4;
														interfaceType = scriptIntValues[isp + 1];
														componentId = scriptIntValues[isp];
														i = scriptIntValues[isp + 3];
														j = scriptIntValues[isp + 2];
														Camera.setCameraLookAtTarget(interfaceType, (componentId & 0x3FFF) - Camera.sceneBaseTileZ, j, (componentId >> 14 & 0x3FFF) - Camera.sceneBaseTileX, i);
														continue;
													}
													if (opcode == 5502) {
														isp -= 6;
														componentId = scriptIntValues[isp];
														if (componentId >= 2) {
															throw new RuntimeException();
														}
														Camera.cameraSplineId = componentId;
														interfaceType = scriptIntValues[isp + 1];
														if (Camera.cameraSplines[Camera.cameraSplineId].length >> 1 <= interfaceType + 1) {
															throw new RuntimeException();
														}
														Camera.positionKeyframe = interfaceType;
														Camera.animationTimer = 0;
														Camera.minAnimationSpeed = scriptIntValues[isp + 2];
														Camera.maxAnimationSpeed = scriptIntValues[isp + 3];
														j = scriptIntValues[isp + 4];
														if (j >= 2) {
															throw new RuntimeException();
														}
														Camera.lookAtSplineId = j;
														i = scriptIntValues[isp + 5];
														if (Camera.cameraSplines[Camera.lookAtSplineId].length >> 1 <= i + 1) {
															throw new RuntimeException();
														}
														Camera.lookAtKeyframe = i;
														Camera.cameraType = 3;
														continue;
													}
													if (opcode == 5503) {
														Camera.resetCameraEffects();
														continue;
													}
													if (opcode == 5504) {
														isp -= 2;
														Camera.orbitCameraPitch = scriptIntValues[isp];
														Camera.orbitCameraYaw = scriptIntValues[isp + 1];
														if (Camera.cameraType == 2) {
															Camera.cameraYaw = Camera.orbitCameraYaw;
															Camera.cameraPitch = Camera.orbitCameraPitch;
														}
														SceneCamera.clampCameraAngle();
														continue;
													}
													if (opcode == 5505) {
														scriptIntValues[isp++] = Camera.orbitCameraPitch;
														continue;
													}
													if (opcode == 5506) {
														scriptIntValues[isp++] = Camera.orbitCameraYaw;
														continue;
													}
												} else if (opcode < 5700) {
													if (opcode == 5600) {
														ssp -= 2;
														chatTyped = scriptStringValues[ssp];
														chatTypedLowercase = scriptStringValues[ssp + 1];
														isp--;
														j = scriptIntValues[isp];
														if (Client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0 && WorldList.step == 0) {
															LoginManager.login(chatTyped, chatTypedLowercase, j);
														}
														continue;
													}
													if (opcode == 5601) {
														LoginManager.advanceLoginStep();
														continue;
													}
													if (opcode == 5602) {
														if (LoginManager.step == 0) {
															LoginManager.reply = -2;
														}
														continue;
													}
													if (opcode == 5603) {
														isp -= 4;
														if (Client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0 && WorldList.step == 0) {
															CreateManager.checkInfo(scriptIntValues[isp + 2], scriptIntValues[isp + 3], scriptIntValues[isp], scriptIntValues[isp + 1]);
														}
														continue;
													}
													if (opcode == 5604) {
														ssp--;
														if (Client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0 && WorldList.step == 0) {
															CreateManager.checkName(scriptStringValues[ssp].encode37());
														}
														continue;
													}
													if (opcode == 5605) {
														isp -= 4;
														ssp -= 2;
														if (Client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0 && WorldList.step == 0) {
															CreateManager.createAccount(scriptIntValues[isp], scriptIntValues[isp + 3], scriptIntValues[isp + 1], scriptStringValues[ssp + 1], scriptStringValues[ssp].encode37(), scriptIntValues[isp + 2]);
														}
														continue;
													}
													if (opcode == 5606) {
														if (CreateManager.step == 0) {
															CreateManager.reply = -2;
														}
														continue;
													}
													if (opcode == 5607) {
														scriptIntValues[isp++] = LoginManager.reply;
														continue;
													}
													if (opcode == 5608) {
														scriptIntValues[isp++] = LoginManager.hopTime;
														continue;
													}
													if (opcode == 5609) {
														scriptIntValues[isp++] = CreateManager.reply;
														continue;
													}
													if (opcode == 5610) {
														for (componentId = 0; componentId < 5; componentId++) {
															scriptStringValues[ssp++] = CreateManager.suggestedNames.length > componentId ? CreateManager.suggestedNames[componentId].toTitleCase() : EMPTY_STRING;
														}
														CreateManager.suggestedNames = null;
														continue;
													}
													if (opcode == 5611) {
														scriptIntValues[isp++] = LoginManager.disallowResult;
														continue;
													}
												} else if (opcode < 6100) {
													if (opcode == 6001) {
														isp--;
														int brightness = scriptIntValues[isp];
														if (brightness < 1) {
															brightness = 1;
														}
														if (brightness > 4) {
															brightness = 4;
														}
														Preferences.brightness = brightness;
														if (!GlRenderer.enabled || !Preferences.highDetailLighting) {
															if (Preferences.brightness == 1) {
																Rasterizer.setBrightness(0.9F);
															}
															if (Preferences.brightness == 2) {
																Rasterizer.setBrightness(0.8F);
															}
															if (Preferences.brightness == 3) {
																Rasterizer.setBrightness(0.7F);
															}
															if (Preferences.brightness == 4) {
																Rasterizer.setBrightness(0.6F);
															}
														}
														if (GlRenderer.enabled) {
															FogManager.setInstantFade();
															if (!Preferences.highDetailLighting) {
																method2742();
															}
														}
														ObjTypeList.clearSprites();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6002) {
														isp--;
														Preferences.setLowmem(scriptIntValues[isp] == 1);
														LocTypeList.clear();
														method2742();
														method2218();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6003) {
														isp--;
														Preferences.roofsVisible = scriptIntValues[isp] == 1;
														method2218();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6005) {
														isp--;
														Preferences.showGroundDecorations = scriptIntValues[isp] == 1;
														method2742();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6006) {
														isp--;
														Preferences.highDetailTextures = scriptIntValues[isp] == 1;
														((Js5TextureProvider) Rasterizer.textureProvider).method3245(!Preferences.highDetailTextures);
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6007) {
														isp--;
														Preferences.manyIdleAnimations = scriptIntValues[isp] == 1;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6008) {
														isp--;
														Preferences.flickeringEffectsOn = scriptIntValues[isp] == 1;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6009) {
														isp--;
														Preferences.manyGroundTextures = scriptIntValues[isp] == 1;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6010) {
														isp--;
														Preferences.characterShadowsOn = scriptIntValues[isp] == 1;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6011) {
														isp--;
														componentId = scriptIntValues[isp];
														if (componentId < 0 || componentId > 2) {
															componentId = 0;
														}
														Preferences.sceneryShadowsType = componentId;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6012) {
														if (GlRenderer.enabled) {
															MaterialManager.setMaterial(0, 0);
														}
														isp--;
														Preferences.highDetailLighting = scriptIntValues[isp] == 1;
														if (GlRenderer.enabled && Preferences.highDetailLighting) {
															Rasterizer.setBrightness(0.7F);
														} else {
															if (Preferences.brightness == 1) {
																Rasterizer.setBrightness(0.9F);
															}
															if (Preferences.brightness == 2) {
																Rasterizer.setBrightness(0.8F);
															}
															if (Preferences.brightness == 3) {
																Rasterizer.setBrightness(0.7F);
															}
															if (Preferences.brightness == 4) {
																Rasterizer.setBrightness(0.6F);
															}
														}
														method2742();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6014) {
														isp--;
														Preferences.highWaterDetail = scriptIntValues[isp] == 1;
														if (GlRenderer.enabled) {
															method2742();
														}
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6015) {
														isp--;
														Preferences.fogEnabled = scriptIntValues[isp] == 1;
														if (GlRenderer.enabled) {
															FogManager.setInstantFade();
														}
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6016) {
														isp--;
														componentId = scriptIntValues[isp];
														if (GlRenderer.enabled) {
															GameShell.canvasReplaceRecommended = true;
														}
														if (componentId < 0 || componentId > 2) {
															componentId = 0;
														}
														Preferences.antiAliasingMode = componentId;
														continue;
													}
													if (opcode == 6017) {
														isp--;
														Preferences.stereo = scriptIntValues[isp] == 1;
														Client.method930();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6018) {
														isp--;
														componentId = scriptIntValues[isp];
														if (componentId < 0) {
															componentId = 0;
														}
														if (componentId > 127) {
															componentId = 127;
														}
														Preferences.soundEffectVolume = componentId;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6019) {
														isp--;
														componentId = scriptIntValues[isp];
														if (componentId < 0) {
															componentId = 0;
														}
														if (componentId > 255) {
															componentId = 255;
														}
														if (componentId != Preferences.musicVolume) {
															if (Preferences.musicVolume == 0 && MusicPlayer.groupId != -1) {
																MidiPlayer.playImmediate(Client.js5Archive6, MusicPlayer.groupId, componentId);
																MidiPlayer.jingle = false;
															} else if (componentId == 0) {
																MidiPlayer.method4548();
																MidiPlayer.jingle = false;
															} else {
																MidiPlayer.method3956(componentId);
															}
															Preferences.musicVolume = componentId;
														}
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6020) {
														isp--;
														componentId = scriptIntValues[isp];
														if (componentId < 0) {
															componentId = 0;
														}
														if (componentId > 127) {
															componentId = 127;
														}
														Preferences.ambientSoundsVolume = componentId;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6021) {
														isp--;
														neverRemoveRoofs = scriptIntValues[isp] == 1;
														method2218();
														continue;
													}
													if (opcode == 6023) {
														isp--;
														componentId = scriptIntValues[isp];
														if (componentId < 0) {
															componentId = 0;
														}
														if (componentId > 2) {
															componentId = 2;
														}
														local1552 = false;
														if (GameShell.maxMemory < 96) {
															local1552 = true;
															componentId = 0;
														}
														Preferences.setParticles(componentId);
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														scriptIntValues[isp++] = local1552 ? 0 : 1;
														continue;
													}
													if (opcode == 6024) {
														isp--;
														componentId = scriptIntValues[isp];
														if (componentId < 0 || componentId > 2) {
															componentId = 0;
														}
														Preferences.windowMode = componentId;
														Preferences.write(GameShell.signLink);
														continue;
													}
													if (opcode == 6028) {
														isp--;
														Preferences.cursorsEnabled = scriptIntValues[isp] != 0;
														Preferences.write(GameShell.signLink);
														continue;
													}
												} else if (opcode < 6200) {
													if (opcode == 6101) {
														scriptIntValues[isp++] = Preferences.brightness;
														continue;
													}
													if (opcode == 6102) {
														scriptIntValues[isp++] = SceneGraph.allLevelsAreVisible() ? 1 : 0;
														continue;
													}
													if (opcode == 6103) {
														scriptIntValues[isp++] = Preferences.roofsVisible ? 1 : 0;
														continue;
													}
													if (opcode == 6105) {
														scriptIntValues[isp++] = Preferences.showGroundDecorations ? 1 : 0;
														continue;
													}
													if (opcode == 6106) {
														scriptIntValues[isp++] = Preferences.highDetailTextures ? 1 : 0;
														continue;
													}
													if (opcode == 6107) {
														scriptIntValues[isp++] = Preferences.manyIdleAnimations ? 1 : 0;
														continue;
													}
													if (opcode == 6108) {
														scriptIntValues[isp++] = Preferences.flickeringEffectsOn ? 1 : 0;
														continue;
													}
													if (opcode == 6109) {
														scriptIntValues[isp++] = Preferences.manyGroundTextures ? 1 : 0;
														continue;
													}
													if (opcode == 6110) {
														scriptIntValues[isp++] = Preferences.characterShadowsOn ? 1 : 0;
														continue;
													}
													if (opcode == 6111) {
														scriptIntValues[isp++] = Preferences.sceneryShadowsType;
														continue;
													}
													if (opcode == 6112) {
														scriptIntValues[isp++] = Preferences.highDetailLighting ? 1 : 0;
														continue;
													}
													if (opcode == 6114) {
														scriptIntValues[isp++] = Preferences.highWaterDetail ? 1 : 0;
														continue;
													}
													if (opcode == 6115) {
														scriptIntValues[isp++] = Preferences.fogEnabled ? 1 : 0;
														continue;
													}
													if (opcode == 6116) {
														scriptIntValues[isp++] = Preferences.antiAliasingMode;
														continue;
													}
													if (opcode == 6117) {
														scriptIntValues[isp++] = Preferences.stereo ? 1 : 0;
														continue;
													}
													if (opcode == 6118) {
														scriptIntValues[isp++] = Preferences.soundEffectVolume;
														continue;
													}
													if (opcode == 6119) {
														scriptIntValues[isp++] = Preferences.musicVolume;
														continue;
													}
													if (opcode == 6120) {
														scriptIntValues[isp++] = Preferences.ambientSoundsVolume;
														continue;
													}
													if (opcode == 6121) {
														if (GlRenderer.enabled) {
															scriptIntValues[isp++] = GlRenderer.arbMultisampleSupported ? 1 : 0;
														} else {
															scriptIntValues[isp++] = 0;
														}
														continue;
													}
													if (opcode == 6123) {
														scriptIntValues[isp++] = Preferences.getParticleSetting();
														continue;
													}
													if (opcode == 6124) {
														scriptIntValues[isp++] = Preferences.windowMode;
														continue;
													}
													if (opcode == 6128) {
														scriptIntValues[isp++] = Preferences.cursorsEnabled ? 1 : 0;
														continue;
													}
												} else if (opcode < 6300) {
													if (opcode == 6200) {
														isp -= 2;
														aShort25 = (short) scriptIntValues[isp];
														if (aShort25 <= 0) {
															aShort25 = 256;
														}
														aShort9 = (short) scriptIntValues[isp + 1];
														if (aShort9 <= 0) {
															aShort9 = 205;
														}
														continue;
													}
													if (opcode == 6201) {
														isp -= 2;
														aShort30 = (short) scriptIntValues[isp];
														if (aShort30 <= 0) {
															aShort30 = 256;
														}
														aShort27 = (short) scriptIntValues[isp + 1];
														if (aShort27 <= 0) {
															aShort27 = 320;
														}
														continue;
													}
													if (opcode == 6202) {
														isp -= 4;
														aShort22 = (short) scriptIntValues[isp];
														if (aShort22 <= 0) {
															aShort22 = 1;
														}
														aShort1 = (short) scriptIntValues[isp + 1];
														if (aShort1 <= 0) {
															aShort1 = 32767;
														} else if (aShort22 > aShort1) {
															aShort1 = aShort22;
														}
														aShort12 = (short) scriptIntValues[isp + 2];
														if (aShort12 <= 0) {
															aShort12 = 1;
														}
														aShort21 = (short) scriptIntValues[isp + 3];
														if (aShort21 <= 0) {
															aShort21 = 32767;
														} else if (aShort21 < aShort12) {
															aShort21 = aShort12;
														}
														continue;
													}
													if (opcode == 6203) {
														method2314(ComponentList.specialComponent.width, 0, ComponentList.specialComponent.height, 0, false);
														scriptIntValues[isp++] = anInt4055;
														scriptIntValues[isp++] = anInt5377;
														continue;
													}
													if (opcode == 6204) {
														scriptIntValues[isp++] = aShort30;
														scriptIntValues[isp++] = aShort27;
														continue;
													}
													if (opcode == 6205) {
														scriptIntValues[isp++] = aShort25;
														scriptIntValues[isp++] = aShort9;
														continue;
													}
												} else if (opcode < 6400) {
													if (opcode == 6300) {
														scriptIntValues[isp++] = (int) (MonotonicTime.currentTimeMillis() / 60000L);
														continue;
													}
													if (opcode == 6301) {
														scriptIntValues[isp++] = (int) (MonotonicTime.currentTimeMillis() / 86400000L) - 11745;
														continue;
													}
													if (opcode == 6302) {
														isp -= 3;
														j = scriptIntValues[isp + 2];
														interfaceType = scriptIntValues[isp + 1];
														componentId = scriptIntValues[isp];
														aCalendar2.clear();
														aCalendar2.set(11, 12);
														aCalendar2.set(j, interfaceType, componentId);
														scriptIntValues[isp++] = (int) (aCalendar2.getTime().getTime() / 86400000L) - 11745;
														continue;
													}
													if (opcode == 6303) {
														aCalendar2.clear();
														aCalendar2.setTime(new Date(MonotonicTime.currentTimeMillis()));
														scriptIntValues[isp++] = aCalendar2.get(1);
														continue;
													}
													if (opcode == 6304) {
														local1552 = true;
														isp--;
														componentId = scriptIntValues[isp];
														if (componentId < 0) {
															local1552 = (componentId + 1) % 4 == 0;
														} else if (componentId < 1582) {
															local1552 = componentId % 4 == 0;
														} else if (componentId % 4 != 0) {
															local1552 = false;
														} else if (componentId % 100 != 0) {
															local1552 = true;
														} else if (componentId % 400 != 0) {
															local1552 = false;
														}
														scriptIntValues[isp++] = local1552 ? 1 : 0;
														continue;
													}
												} else if (opcode < 6500) {
													if (opcode == 6405) {
														// scriptIntValues[isp++] = client.showVideoAd() ? 1 : 0;
														scriptIntValues[isp++] = 0;
														continue;
													}
													if (opcode == 6406) {
														// scriptIntValues[isp++] = isShowingVideoAd() ? 1 : 0;
														scriptIntValues[isp++] = 0;
														continue;
													}
												} else if (opcode < 6600) {
													if (opcode == 6500) {
														if (Client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0) {
															scriptIntValues[isp++] = WorldList.fetch() == -1 ? 0 : 1;
															continue;
														}
														scriptIntValues[isp++] = 1;
														continue;
													}
													@Pc(10247) WorldInfo local10247;
													@Pc(10191) World local10191;
													if (opcode == 6501) {
														local10191 = WorldList.getFirstWorld();
														if (local10191 == null) {
															scriptIntValues[isp++] = -1;
															scriptIntValues[isp++] = 0;
															scriptStringValues[ssp++] = EMPTY_STRING;
															scriptIntValues[isp++] = 0;
															scriptStringValues[ssp++] = EMPTY_STRING;
															scriptIntValues[isp++] = 0;
														} else {
															scriptIntValues[isp++] = local10191.id;
															scriptIntValues[isp++] = local10191.flags;
															scriptStringValues[ssp++] = local10191.activity;
															local10247 = local10191.getWorldInfo();
															scriptIntValues[isp++] = local10247.flag;
															scriptStringValues[ssp++] = local10247.name;
															scriptIntValues[isp++] = local10191.players;
														}
														continue;
													}
													if (opcode == 6502) {
														local10191 = WorldList.getNextWorld();
														if (local10191 == null) {
															scriptIntValues[isp++] = -1;
															scriptIntValues[isp++] = 0;
															scriptStringValues[ssp++] = EMPTY_STRING;
															scriptIntValues[isp++] = 0;
															scriptStringValues[ssp++] = EMPTY_STRING;
															scriptIntValues[isp++] = 0;
														} else {
															scriptIntValues[isp++] = local10191.id;
															scriptIntValues[isp++] = local10191.flags;
															scriptStringValues[ssp++] = local10191.activity;
															local10247 = local10191.getWorldInfo();
															scriptIntValues[isp++] = local10247.flag;
															scriptStringValues[ssp++] = local10247.name;
															scriptIntValues[isp++] = local10191.players;
														}
														continue;
													}
													if (opcode == 6503) {
														isp--;
														componentId = scriptIntValues[isp];
														if (Client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0) {
															scriptIntValues[isp++] = WorldList.hopWorld(componentId) ? 1 : 0;
															continue;
														}
														scriptIntValues[isp++] = 0;
														continue;
													}
													if (opcode == 6504) {
														isp--;
														Preferences.lastWorldId = scriptIntValues[isp];
														Preferences.write(GameShell.signLink);
														continue;
													}
													if (opcode == 6505) {
														scriptIntValues[isp++] = Preferences.lastWorldId;
														continue;
													}
													if (opcode == 6506) {
														isp--;
														componentId = scriptIntValues[isp];
														@Pc(10440) World local10440 = getWorld(componentId);
														if (local10440 == null) {
															scriptIntValues[isp++] = -1;
															scriptStringValues[ssp++] = EMPTY_STRING;
															scriptIntValues[isp++] = 0;
															scriptStringValues[ssp++] = EMPTY_STRING;
															scriptIntValues[isp++] = 0;
														} else {
															scriptIntValues[isp++] = local10440.flags;
															scriptStringValues[ssp++] = local10440.activity;
															@Pc(10458) WorldInfo local10458 = local10440.getWorldInfo();
															scriptIntValues[isp++] = local10458.flag;
															scriptStringValues[ssp++] = local10458.name;
															scriptIntValues[isp++] = local10440.players;
														}
														continue;
													}
													if (opcode == 6507) {
														isp -= 4;
														j = scriptIntValues[isp + 2];
														componentId = scriptIntValues[isp];
														local7566 = scriptIntValues[isp + 3] == 1;
														local1552 = scriptIntValues[isp + 1] == 1;
														WorldList.sortWorldList(j, local1552, componentId, local7566);
														continue;
													}
												} else if (opcode < 6700) {
													if (opcode == 6600) {
														isp--;
														Preferences.aBoolean63 = scriptIntValues[isp] == 1;
														Preferences.write(GameShell.signLink);
														continue;
													}
													if (opcode == 6601) {
														scriptIntValues[isp++] = Preferences.aBoolean63 ? 1 : 0;
														continue;
													}
												}
											}
										} else if (opcode == 4500) {
											isp -= 2;
											componentId = scriptIntValues[isp];
											interfaceType = scriptIntValues[isp + 1];
											local5294 = ParamTypeList.get(interfaceType);
											if (local5294.isString()) {
												scriptStringValues[ssp++] = StructTypeList.get(componentId).getParam(local5294.defaultString, interfaceType);
											} else {
												scriptIntValues[isp++] = StructTypeList.get(componentId).method2798(interfaceType, local5294.defaultInt);
											}
											continue;
										}
									} else if (opcode == 4400) {
										isp -= 2;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										local5294 = ParamTypeList.get(interfaceType);
										if (local5294.isString()) {
											scriptStringValues[ssp++] = LocTypeList.get(componentId).getParam(local5294.defaultString, interfaceType);
										} else {
											scriptIntValues[isp++] = LocTypeList.get(componentId).getParam(local5294.defaultInt, interfaceType);
										}
										continue;
									}
								} else {
									if (opcode == 4100) {
										// append_num
										ssp--;
										chatTyped = scriptStringValues[ssp];
										isp--;
										interfaceType = scriptIntValues[isp];
										scriptStringValues[ssp++] = JString.concatenate(new JString[] { chatTyped, JString.parseInt(interfaceType) });
										continue;
									}
									if (opcode == 4101) {
										// append
										ssp -= 2;
										chatTypedLowercase = scriptStringValues[ssp + 1];
										chatTyped = scriptStringValues[ssp];
										scriptStringValues[ssp++] = JString.concatenate(new JString[] { chatTyped, chatTypedLowercase });
										continue;
									}
									if (opcode == 4102) {
										// append_signnum
										ssp--;
										chatTyped = scriptStringValues[ssp];
										isp--;
										interfaceType = scriptIntValues[isp];
										scriptStringValues[ssp++] = JString.concatenate(new JString[] { chatTyped, JString.parseIntTrue(interfaceType) });
										continue;
									}
									if (opcode == 4103) {
										// lowercase
										ssp--;
										chatTyped = scriptStringValues[ssp];
										scriptStringValues[ssp++] = chatTyped.toLowerCase();
										continue;
									}
									if (opcode == 4104) {
										// fromdate
										isp--;
										componentId = scriptIntValues[isp];
										@Pc(11770) long local11770 = (long) componentId * 86400000L + 1014768000000L;
										aCalendar2.setTime(new Date(local11770));
										i = aCalendar2.get(5);
										start = aCalendar2.get(2);
										childId = aCalendar2.get(1);
										scriptStringValues[ssp++] = JString.concatenate(new JString[] { JString.parseInt(i), aClass100_767, DateUtil.aClass100Array40[start], aClass100_767, JString.parseInt(childId) });
										continue;
									}
									if (opcode == 4105) {
										// text_gender
										ssp -= 2;
										chatTypedLowercase = scriptStringValues[ssp + 1];
										chatTyped = scriptStringValues[ssp];
										if (PlayerList.self.appearance != null && PlayerList.self.appearance.gender) {
											scriptStringValues[ssp++] = chatTypedLowercase;
											continue;
										}
										scriptStringValues[ssp++] = chatTyped;
										continue;
									}
									if (opcode == 4106) {
										// tostring
										isp--;
										componentId = scriptIntValues[isp];
										scriptStringValues[ssp++] = JString.parseInt(componentId);
										continue;
									}
									if (opcode == 4107) {
										// compare
										ssp -= 2;
										scriptIntValues[isp++] = scriptStringValues[ssp].compare(scriptStringValues[ssp + 1]);
										continue;
									}
									if (opcode == 4108) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										isp -= 2;
										j = scriptIntValues[isp + 1];
										interfaceType = scriptIntValues[isp];
										scriptIntValues[isp++] = FontMetricsList.get(j).getParagraphLineCount(chatTyped, interfaceType);
										continue;
									}
									if (opcode == 4109) {
										isp -= 2;
										ssp--;
										chatTyped = scriptStringValues[ssp];
										j = scriptIntValues[isp + 1];
										interfaceType = scriptIntValues[isp];
										scriptIntValues[isp++] = FontMetricsList.get(j).getMaxLineWidth(chatTyped, interfaceType);
										continue;
									}
									if (opcode == 4110) {
										ssp -= 2;
										chatTyped = scriptStringValues[ssp];
										chatTypedLowercase = scriptStringValues[ssp + 1];
										isp--;
										if (scriptIntValues[isp] == 1) {
											scriptStringValues[ssp++] = chatTyped;
										} else {
											scriptStringValues[ssp++] = chatTypedLowercase;
										}
										continue;
									}
									if (opcode == 4111) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										scriptStringValues[ssp++] = Font.escape(chatTyped);
										continue;
									}
									if (opcode == 4112) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										isp--;
										interfaceType = scriptIntValues[isp];
										if (interfaceType == -1) {
											throw new RuntimeException("null char");
										}
										scriptStringValues[ssp++] = chatTyped.concatChar(interfaceType);
										continue;
									}
									if (opcode == 4113) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = CharUtils.isValidChar(componentId) ? 1 : 0;
										continue;
									}
									if (opcode == 4114) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = CharUtils.method433(componentId) ? 1 : 0;
										continue;
									}
									if (opcode == 4115) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = CharUtils.isLetter(componentId) ? 1 : 0;
										continue;
									}
									if (opcode == 4116) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = CharUtils.isDigit(componentId) ? 1 : 0;
										continue;
									}
									if (opcode == 4117) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										if (chatTyped == null) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = chatTyped.length();
										}
										continue;
									}
									if (opcode == 4118) {
										isp -= 2;
										ssp--;
										chatTyped = scriptStringValues[ssp];
										interfaceType = scriptIntValues[isp];
										j = scriptIntValues[isp + 1];
										scriptStringValues[ssp++] = chatTyped.substring(j, interfaceType);
										continue;
									}
									if (opcode == 4119) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										chatTypedLowercase = JString.allocate(chatTyped.length());
										@Pc(12220) boolean local12220 = false;
										for (i = 0; i < chatTyped.length(); i++) {
											start = chatTyped.charAt(i);
											if (start == 60) {
												local12220 = true;
											} else if (start == 62) {
												local12220 = false;
											} else if (!local12220) {
												chatTypedLowercase.append(start);
											}
										}
										chatTypedLowercase.method3156();
										scriptStringValues[ssp++] = chatTypedLowercase;
										continue;
									}
									if (opcode == 4120) {
										isp -= 2;
										ssp--;
										chatTyped = scriptStringValues[ssp];
										interfaceType = scriptIntValues[isp];
										j = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = chatTyped.indexOf(interfaceType, j);
										continue;
									}
									if (opcode == 4121) {
										ssp -= 2;
										chatTyped = scriptStringValues[ssp];
										chatTypedLowercase = scriptStringValues[ssp + 1];
										isp--;
										j = scriptIntValues[isp];
										scriptIntValues[isp++] = chatTyped.indexOf(chatTypedLowercase, j);
										continue;
									}
									if (opcode == 4122) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = CharUtils.toLowerCase(componentId);
										continue;
									}
									if (opcode == 4123) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = CharUtils.toUpperCase(componentId);
										continue;
									}
									if (opcode == 4124) {
										isp--;
										local12388 = scriptIntValues[isp] != 0;
										isp--;
										interfaceType = scriptIntValues[isp];
										scriptStringValues[ssp++] = StringUtils.formatNumber(Client.language, local12388, 0, (long) interfaceType);
										continue;
									}
								}
							}
						} else {
							if (opcode < 2000) {
								createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
							} else {
								isp--;
								createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
								opcode -= 1000;
							}
							if (opcode == 1000) {
								// setposition
								isp -= 4;
								createdComponent.baseX = scriptIntValues[isp];
								createdComponent.baseY = scriptIntValues[isp + 1];
								j = scriptIntValues[isp + 3];
								if (j < 0) {
									j = 0;
								} else if (j > 5) {
									j = 5;
								}
								interfaceType = scriptIntValues[isp + 2];
								if (interfaceType < 0) {
									interfaceType = 0;
								} else if (interfaceType > 5) {
									interfaceType = 5;
								}
								createdComponent.xMode = (byte) j;
								createdComponent.yMode = (byte) interfaceType;
								ComponentList.redraw(createdComponent);
								ComponentList.update(createdComponent);
								if (createdComponent.createdComponentId == -1) {
									DelayedStateChange.setComponentPositionClient(createdComponent.id);
								}
								continue;
							}
							if (opcode == 1001) {
								// setsize
								isp -= 4;
								createdComponent.baseWidth = scriptIntValues[isp];
								createdComponent.baseHeight = scriptIntValues[isp + 1];
								createdComponent.anInt451 = 0;
								createdComponent.anInt526 = 0;
								interfaceType = scriptIntValues[isp + 2];
								j = scriptIntValues[isp + 3];
								if (j < 0) {
									j = 0;
								} else if (j > 4) {
									j = 4;
								}
								createdComponent.dynamicHeightValue = (byte) j;
								if (interfaceType < 0) {
									interfaceType = 0;
								} else if (interfaceType > 4) {
									interfaceType = 4;
								}
								createdComponent.dynamicWidthValue = (byte) interfaceType;
								ComponentList.redraw(createdComponent);
								ComponentList.update(createdComponent);
								if (createdComponent.type == 0) {
									ComponentList.updateContainerLayout(createdComponent, false);
								}
								continue;
							}
							if (opcode == 1003) {
								// sethide
								isp--;
								local1552 = scriptIntValues[isp] == 1;
								if (local1552 != createdComponent.hidden) {
									createdComponent.hidden = local1552;
									ComponentList.redraw(createdComponent);
								}
								if (createdComponent.createdComponentId == -1) {
									DelayedStateChange.setComponentHiddenClient(createdComponent.id);
								}
								continue;
							}
							if (opcode == 1004) {
								// setaspect
								isp -= 2;
								createdComponent.aspectWidth = scriptIntValues[isp];
								createdComponent.aspectHeight = scriptIntValues[isp + 1];
								ComponentList.redraw(createdComponent);
								ComponentList.update(createdComponent);
								if (createdComponent.type == 0) {
									ComponentList.updateContainerLayout(createdComponent, false);
								}
								continue;
							}
							if (opcode == 1005) {
								isp--;
								createdComponent.noClickThrough = scriptIntValues[isp] == 1;
								continue;
							}
						}
					}
				}
				throw new IllegalStateException();
			}
		} catch (@Pc(14378) Exception local14378) {
			if (clientScript.name == null) {
				if (Client.modeWhere != 0) {
					Chat.addMessage(EMPTY_STRING, 0, CS_ERROR);
				}
				TracingException.report("CS2 - scr:" + clientScript.nodeId + " op:" + op, local14378);
			} else {
				@Pc(14385) JString local14385 = JString.allocate(30);
				local14385.method3113(aClass100_928).method3113(clientScript.name);
				for (cycles = fp - 1; cycles >= 0; cycles--) {
					local14385.method3113(aClass100_253).method3113(callStack[cycles].script.name);
				}
				if (op == 40) {
					cycles = intOperands[pc];
					local14385.method3113(aClass100_802).method3113(JString.parseInt(cycles));
				}
				if (Client.modeWhere != 0) {
					Chat.addMessage(EMPTY_STRING, 0, JString.concatenate(new JString[] { aClass100_780, clientScript.name}));
				}
				TracingException.report("CS2 - scr:" + clientScript.nodeId + " op:" + op + new String(local14385.method3148()), local14378);
			}
		}
	}

	@OriginalMember(owner = "client!gn", name = "b", descriptor = "(Z)V")
	public static void method1807() {
		for (@Pc(11) int local11 = 0; local11 < 100; local11++) {
			ComponentList.componentNeedsRedraw[local11] = true;
		}
	}

	@OriginalMember(owner = "runetek4.client!wa", name = "o", descriptor = "(I)V")
	public static void method2218() {
		@Pc(8) int local8 = getRoofVisibilityMode();
		if (local8 == 0) {
			tileMarkings = null;
			method3993(0);
		} else if (local8 == 1) {
			method960((byte) 0);
			method3993(512);
			method2608();
		} else {
			method960((byte) (anInt3325 - 4 & 0xFF));
			method3993(2);
		}
	}

	@OriginalMember(owner = "runetek4.client!q", name = "a", descriptor = "(IIIIIIBI)V")
	public static void calculateScreenCoordinates(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(7) int arg6) {
		if (arg5 < 128 || arg2 < 128 || arg5 > 13056 || arg2 > 13056) {
			overheadScreenY = -1;
			overheadScreenX = -1;
			return;
		}
		@Pc(38) int local38 = SceneGraph.getTileHeight(Player.plane, arg5, arg2) - arg3;
		@Pc(42) int local42 = arg2 - Camera.renderZ;
		@Pc(46) int local46 = local38 - Camera.cameraY;
		@Pc(50) int local50 = arg5 - Camera.renderX;
		@Pc(54) int local54 = MathUtils.sin[Camera.cameraPitch];
		@Pc(58) int local58 = MathUtils.cos[Camera.cameraPitch];
		@Pc(62) int local62 = MathUtils.sin[Camera.cameraYaw];
		@Pc(66) int local66 = MathUtils.cos[Camera.cameraYaw];
		@Pc(76) int local76 = local50 * local66 + local62 * local42 >> 16;
		@Pc(87) int local87 = local42 * local66 - local62 * local50 >> 16;
		@Pc(89) int local89 = local76;
		@Pc(99) int local99 = local58 * local46 - local87 * local54 >> 16;
		@Pc(113) int local113 = local87 * local58 + local46 * local54 >> 16;
		if (local113 < 50) {
			overheadScreenY = -1;
			overheadScreenX = -1;
		} else if (GlRenderer.enabled) {
			@Pc(150) int local150 = arg1 * 512 >> 8;
			overheadScreenX = local150 * local89 / local113 + arg0;
			@Pc(164) int local164 = arg6 * 512 >> 8;
			overheadScreenY = local164 * local99 / local113 + arg4;
		} else {
			overheadScreenX = (local89 << 9) / local113 + arg0;
			overheadScreenY = (local99 << 9) / local113 + arg4;
		}
	}

	@OriginalMember(owner = "runetek4.client!ja", name = "a", descriptor = "(IIIIIZ)V")
	public static void method2314(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) boolean arg4) {
		if (arg0 < 1) {
			arg0 = 1;
		}
		if (arg2 < 1) {
			arg2 = 1;
		}
		if (GlRenderer.enabled) {
			@Pc(25) int local25 = arg2 - 334;
			if (local25 < 0) {
				local25 = 0;
			} else if (local25 > 100) {
				local25 = 100;
			}
			@Pc(51) int local51 = local25 * (aShort9 - aShort25) / 100 + aShort25;
			if (aShort22 > local51) {
				local51 = aShort22;
			} else if (aShort1 < local51) {
				local51 = aShort1;
			}
			@Pc(73) int local73 = local51 * arg2 * 512 / (arg0 * 334);
			@Pc(115) int local115;
			@Pc(122) int local122;
			@Pc(86) short local86;
			if (local73 < aShort12) {
				local86 = aShort12;
				local51 = arg0 * 334 * local86 / (arg2 * 512);
				if (aShort1 < local51) {
					local51 = aShort1;
					local115 = arg2 * 512 * local51 / (local86 * 334);
					local122 = (arg0 - local115) / 2;
					if (arg4) {
						GlRaster.method1177();
						GlRaster.fillRect(arg3, arg1, local122, arg2, 0);
						GlRaster.fillRect(arg0 + arg3 - local122, arg1, local122, arg2, 0);
					}
					arg3 += local122;
					arg0 -= local122 * 2;
				}
			} else if (aShort21 < local73) {
				local86 = aShort21;
				local51 = local86 * arg0 * 334 / (arg2 * 512);
				if (aShort22 > local51) {
					local51 = aShort22;
					local115 = local86 * arg0 * 334 / (local51 * 512);
					local122 = (arg2 - local115) / 2;
					if (arg4) {
						GlRaster.method1177();
						GlRaster.fillRect(arg3, arg1, arg0, local122, 0);
						GlRaster.fillRect(arg3, arg1 + arg2 - local122, arg0, local122, 0);
					}
					arg2 -= local122 * 2;
					arg1 += local122;
				}
			}
			anInt5029 = local51 * arg2 / 334;
		}
		anInt4055 = (short) arg0;
		anInt5377 = (short) arg2;
		anInt773 = arg1;
		anInt983 = arg3;
	}

	// TODO move somewhere else
	@OriginalMember(owner = "runetek4.client!lc", name = "a", descriptor = "(IIIIIII)V")
	public static void drawOverheads(@OriginalArg(0) int screenY, @OriginalArg(1) int screenWidth, @OriginalArg(2) int screenX, @OriginalArg(3) int cameraX, @OriginalArg(4) int screenHeight, @OriginalArg(5) int cameraZ) {
		OverHeadChat.sizes = 0;
		@Pc(5) int entityIndex;
		@Pc(642) int local642;
		@Pc(74) int iconYOffset;
		@Pc(265) int overlayHeight;
		@Pc(310) int local310;
		@Pc(359) int local359;
		@Pc(639) int local639;
		for (entityIndex = -1; entityIndex < PlayerList.playerCount + NpcList.npcCount; entityIndex++) {
			@Pc(17) PathingEntity local17;
			if (entityIndex == -1) {
				local17 = PlayerList.self;
			} else if (PlayerList.playerCount > entityIndex) {
				local17 = PlayerList.players[PlayerList.playerIds[entityIndex]];
			} else {
				local17 = NpcList.npcs[NpcList.npcIds[entityIndex - PlayerList.playerCount]];
			}
			if (local17 != null && local17.isVisible()) {
				@Pc(58) NpcType npcType;
				if (local17 instanceof Npc) {
					npcType = ((Npc) local17).type;
					if (npcType.multinpc != null) {
						npcType = npcType.getMultiNPC();
					}
					if (npcType == null) {
						continue;
					}
				}
				@Pc(161) int local161;
				if (entityIndex >= PlayerList.playerCount) {
					npcType = ((Npc) local17).type;
					if (npcType.multinpc != null) {
						npcType = npcType.getMultiNPC();
					}
					if (npcType.headIcon >= 0 && Sprites.headiconPrayers.length > npcType.headIcon) {
						if (npcType.overlayheight == -1) {
							overlayHeight = local17.height() + 15;
						} else {
							overlayHeight = npcType.overlayheight + 15;
						}
						setOverheadScreenCoordinateOffsets(screenHeight >> 1, cameraX, local17, cameraZ, overlayHeight, screenWidth >> 1);
						if (overheadScreenX > -1) {
							Sprites.headiconPrayers[npcType.headIcon].render(screenX + overheadScreenX - 12, screenY + -30 - -overheadScreenY);
						}
					}
					@Pc(308) MapMarker[] local308 = MiniMap.hintMapMarkers;
					for (local310 = 0; local310 < local308.length; local310++) {
						@Pc(322) MapMarker local322 = local308[local310];
						if (local322 != null && local322.type == 1 && local322.actorTargetId == NpcList.npcIds[entityIndex - PlayerList.playerCount] && Client.loop % 20 < 10) {
							if (npcType.overlayheight == -1) {
								local359 = local17.height() + 15;
							} else {
								local359 = npcType.overlayheight + 15;
							}
							setOverheadScreenCoordinateOffsets(screenHeight >> 1, cameraX, local17, cameraZ, local359, screenWidth >> 1);
							if (overheadScreenX > -1) {
								Sprites.headhints[local322.anInt4048].render(screenX + overheadScreenX - 12, overheadScreenY + -28 + screenY);
							}
						}
					}
				} else {
					iconYOffset = 30;
					@Pc(77) Player player = (Player) local17;
					if (player.anInt1669 != -1 || player.anInt1649 != -1) {
						setOverheadScreenCoordinateOffsets(screenHeight >> 1, cameraX, local17, cameraZ, local17.height() + 15, screenWidth >> 1);
						if (overheadScreenX > -1) {
							if (player.anInt1669 != -1) {
								Sprites.headiconPks[player.anInt1669].render(overheadScreenX + screenX - 12, screenY + -30 + overheadScreenY);
								iconYOffset += 25;
							}
							if (player.anInt1649 != -1) {
								Sprites.headiconPrayers[player.anInt1649].render(screenX + overheadScreenX - 12, screenY - (-overheadScreenY + iconYOffset));
								iconYOffset += 25;
							}
						}
					}
					if (entityIndex >= 0) {
						@Pc(159) MapMarker[] local159 = MiniMap.hintMapMarkers;
						for (local161 = 0; local161 < local159.length; local161++) {
							@Pc(173) MapMarker local173 = local159[local161];
							if (local173 != null && local173.type == 10 && PlayerList.playerIds[entityIndex] == local173.actorTargetId) {
								setOverheadScreenCoordinateOffsets(screenHeight >> 1, cameraX, local17, cameraZ, local17.height() + 15, screenWidth >> 1);
								if (overheadScreenX > -1) {
									Sprites.headhints[local173.anInt4048].render(screenX + overheadScreenX - 12, screenY + (overheadScreenY - iconYOffset));
								}
							}
						}
					}
				}
				if (local17.chatMessage != null && (entityIndex >= PlayerList.playerCount || Chat.publicFilter == 0 || Chat.publicFilter == 3 || Chat.publicFilter == 1 && FriendList.contains(((Player) local17).username))) {
					setOverheadScreenCoordinateOffsets(screenHeight >> 1, cameraX, local17, cameraZ, local17.height(), screenWidth >> 1);
					if (overheadScreenX > -1 && OverHeadChat.sizes < OverHeadChat.capacity) {
						OverHeadChat.anIntArray389[OverHeadChat.sizes] = Fonts.b12Full.getStringWidth(local17.chatMessage) / 2;
						OverHeadChat.anIntArray387[OverHeadChat.sizes] = Fonts.b12Full.characterDefaultHeight;
						OverHeadChat.anIntArray385[OverHeadChat.sizes] = overheadScreenX;
						OverHeadChat.anIntArray392[OverHeadChat.sizes] = overheadScreenY;
						OverHeadChat.colors[OverHeadChat.sizes] = local17.chatColor;
						OverHeadChat.effects[OverHeadChat.sizes] = local17.chatEffect;
						OverHeadChat.loops[OverHeadChat.sizes] = local17.chatLoops;
						OverHeadChat.messages[OverHeadChat.sizes] = local17.chatMessage;
						OverHeadChat.sizes++;
					}
				}
				if (local17.hitpointsBarVisibleUntil > Client.loop) {
					@Pc(508) Sprite backgroundBar = Sprites.hitbars[0];
					@Pc(512) Sprite foregroundBar = Sprites.hitbars[1];
					if (local17 instanceof Npc) {
						@Pc(518) Npc npc = (Npc) local17;
						@Pc(528) Sprite[] hitBarSprites = (Sprite[]) HitBarList.hitBars.get((long) npc.type.hitBarId);
						if (hitBarSprites == null) {
							hitBarSprites = SpriteLoader.loadAlphaSprites(npc.type.hitBarId, Client.js5Archive8);
							if (hitBarSprites != null) {
								HitBarList.hitBars.put(hitBarSprites, (long) npc.type.hitBarId);
							}
						}
						if (hitBarSprites != null && hitBarSprites.length == 2) {
							foregroundBar = hitBarSprites[1];
							backgroundBar = hitBarSprites[0];
						}
						@Pc(571) NpcType local571 = npc.type;
						if (local571.overlayheight == -1) {
							local310 = local17.height();
						} else {
							local310 = local571.overlayheight;
						}
					} else {
						local310 = local17.height();
					}
					setOverheadScreenCoordinateOffsets(screenHeight >> 1, cameraX, local17, cameraZ, backgroundBar.height + local310 + 10, screenWidth >> 1);
					if (overheadScreenX > -1) {
						local161 = overheadScreenX + screenX - (backgroundBar.width >> 1);
						local359 = overheadScreenY + screenY - 3;
						backgroundBar.render(local161, local359);
						local639 = backgroundBar.width * local17.hitpointsBar / 255;
						local642 = backgroundBar.height;
						if (GlRenderer.enabled) {
							GlRaster.method1183(local161, local359, local161 + local639, local359 + local642);
						} else {
							SoftwareRenderer.intersectClipBounds(local161, local359, local161 + local639, local642 + local359);
						}
						foregroundBar.render(local161, local359);
						if (GlRenderer.enabled) {
							GlRaster.setClip(screenX, screenY, screenWidth + screenX, screenY - -screenHeight);
						} else {
							SoftwareRenderer.setClip(screenX, screenY, screenWidth + screenX, screenHeight + screenY);
						}
					}
				}
				for (iconYOffset = 0; iconYOffset < 4; iconYOffset++) {
					if (local17.damageCycles[iconYOffset] > Client.loop) {
						if (local17 instanceof Npc) {
							@Pc(725) Npc local725 = (Npc) local17;
							@Pc(728) NpcType local728 = local725.type;
							if (local728.overlayheight == -1) {
								overlayHeight = local17.height() / 2;
							} else {
								overlayHeight = local728.overlayheight / 2;
							}
						} else {
							overlayHeight = local17.height() / 2;
						}
						setOverheadScreenCoordinateOffsets(screenHeight >> 1, cameraX, local17, cameraZ, overlayHeight, screenWidth >> 1);
						if (overheadScreenX > -1) {
							if (iconYOffset == 1) {
								overheadScreenY -= 20;
							}
							if (iconYOffset == 2) {
								overheadScreenY -= 10;
								overheadScreenX -= 15;
							}
							if (iconYOffset == 3) {
								overheadScreenY -= 10;
								overheadScreenX += 15;
							}
							Sprites.hitmarks[local17.damageTypes[iconYOffset]].render(screenX + overheadScreenX - 12, screenY + overheadScreenY - 12);
							Fonts.p11Full.renderCenter(JString.parseInt(local17.damageValues[iconYOffset]), overheadScreenX + screenX - 1, overheadScreenY + 3 + screenY, 16777215, 0);
						}
					}
				}
			}
		}
		for (entityIndex = 0; entityIndex < OverHeadChat.sizes; entityIndex++) {
			iconYOffset = OverHeadChat.anIntArray392[entityIndex];
			@Pc(859) int local859 = OverHeadChat.anIntArray385[entityIndex];
			local310 = OverHeadChat.anIntArray387[entityIndex];
			overlayHeight = OverHeadChat.anIntArray389[entityIndex];
			@Pc(869) boolean local869 = true;
			while (local869) {
				local869 = false;
				for (local359 = 0; local359 < entityIndex; local359++) {
					if (OverHeadChat.anIntArray392[local359] - OverHeadChat.anIntArray387[local359] < iconYOffset + 2 && iconYOffset - local310 < OverHeadChat.anIntArray392[local359] - -2 && local859 - overlayHeight < OverHeadChat.anIntArray385[local359] + OverHeadChat.anIntArray389[local359] && OverHeadChat.anIntArray385[local359] - OverHeadChat.anIntArray389[local359] < overlayHeight + local859 && OverHeadChat.anIntArray392[local359] - OverHeadChat.anIntArray387[local359] < iconYOffset) {
						iconYOffset = OverHeadChat.anIntArray392[local359] - OverHeadChat.anIntArray387[local359];
						local869 = true;
					}
				}
			}
			overheadScreenX = OverHeadChat.anIntArray385[entityIndex];
			overheadScreenY = OverHeadChat.anIntArray392[entityIndex] = iconYOffset;
			@Pc(962) JString chatMessage = OverHeadChat.messages[entityIndex];
			if (VarpDomain.chatEffectsDisabled == 0) {
				local639 = 16776960;
				if (OverHeadChat.colors[entityIndex] < 6) {
					local639 = OverHeadChat.CHAT_COLORS[OverHeadChat.colors[entityIndex]];
				}
				if (OverHeadChat.colors[entityIndex] == 6) {
					local639 = anInt3325 % 20 >= 10 ? 16776960 : 16711680;
				}
				if (OverHeadChat.colors[entityIndex] == 7) {
					local639 = anInt3325 % 20 < 10 ? 255 : 65535;
				}
				if (OverHeadChat.colors[entityIndex] == 8) {
					local639 = anInt3325 % 20 >= 10 ? 8454016 : 45056;
				}
				if (OverHeadChat.colors[entityIndex] == 9) {
					local642 = 150 - OverHeadChat.loops[entityIndex];
					if (local642 < 50) {
						local639 = local642 * 1280 + 16711680;
					} else if (local642 < 100) {
						local639 = 16776960 + 16384000 - local642 * 327680;
					} else if (local642 < 150) {
						local639 = local642 * 5 + 65280 - 500;
					}
				}
				if (OverHeadChat.colors[entityIndex] == 10) {
					local642 = 150 - OverHeadChat.loops[entityIndex];
					if (local642 < 50) {
						local639 = local642 * 5 + 16711680;
					} else if (local642 < 100) {
						local639 = 16711935 - (local642 - 50) * 327680;
					} else if (local642 < 150) {
						local639 = local642 * 327680 + 255 + 500 - local642 * 5 - 32768000;
					}
				}
				if (OverHeadChat.colors[entityIndex] == 11) {
					local642 = 150 - OverHeadChat.loops[entityIndex];
					if (local642 < 50) {
						local639 = 16777215 - local642 * 327685;
					} else if (local642 < 100) {
						local639 = local642 * 327685 + 65280 - 16384250;
					} else if (local642 < 150) {
						local639 = 16777215 + 32768000 - local642 * 327680;
					}
				}
				if (OverHeadChat.effects[entityIndex] == 0) {
					Fonts.b12Full.renderCenter(chatMessage, overheadScreenX + screenX, screenY + overheadScreenY, local639, 0);
				}
				if (OverHeadChat.effects[entityIndex] == 1) {
					Fonts.b12Full.renderWave(chatMessage, screenX + overheadScreenX, overheadScreenY + screenY, local639, anInt3325);
				}
				if (OverHeadChat.effects[entityIndex] == 2) {
					Fonts.b12Full.renderWave2(chatMessage, screenX + overheadScreenX, screenY - -overheadScreenY, local639, anInt3325);
				}
				if (OverHeadChat.effects[entityIndex] == 3) {
					Fonts.b12Full.renderShake(chatMessage, screenX + overheadScreenX, overheadScreenY + screenY, local639, anInt3325, 150 - OverHeadChat.loops[entityIndex]);
				}
				if (OverHeadChat.effects[entityIndex] == 4) {
					local642 = (150 - OverHeadChat.loops[entityIndex]) * (Fonts.b12Full.getStringWidth(chatMessage) + 100) / 150;
					if (GlRenderer.enabled) {
						GlRaster.method1183(overheadScreenX + screenX - 50, screenY, overheadScreenX + screenX + 50, screenHeight + screenY);
					} else {
						SoftwareRenderer.intersectClipBounds(screenX + overheadScreenX - 50, screenY, overheadScreenX + screenX + 50, screenHeight + screenY);
					}
					Fonts.b12Full.renderLeft(chatMessage, screenX + overheadScreenX + 50 - local642, screenY + overheadScreenY, local639, 0);
					if (GlRenderer.enabled) {
						GlRaster.setClip(screenX, screenY, screenWidth + screenX, screenHeight + screenY);
					} else {
						SoftwareRenderer.setClip(screenX, screenY, screenX + screenWidth, screenY + screenHeight);
					}
				}
				if (OverHeadChat.effects[entityIndex] == 5) {
					@Pc(1372) int bounceOffset = 0;
					local642 = 150 - OverHeadChat.loops[entityIndex];
					if (GlRenderer.enabled) {
						GlRaster.method1183(screenX, overheadScreenY + screenY - Fonts.b12Full.characterDefaultHeight - 1, screenWidth + screenX, screenY + overheadScreenY + 5);
					} else {
						SoftwareRenderer.intersectClipBounds(screenX, overheadScreenY + screenY - Fonts.b12Full.characterDefaultHeight - 1, screenX + screenWidth, overheadScreenY + screenY + 5);
					}
					if (local642 < 25) {
						bounceOffset = local642 - 25;
					} else if (local642 > 125) {
						bounceOffset = local642 - 125;
					}
					Fonts.b12Full.renderCenter(chatMessage, overheadScreenX + screenX, bounceOffset + screenY + overheadScreenY, local639, 0);
					if (GlRenderer.enabled) {
						GlRaster.setClip(screenX, screenY, screenX + screenWidth, screenY + screenHeight);
					} else {
						SoftwareRenderer.setClip(screenX, screenY, screenX + screenWidth, screenY + screenHeight);
					}
				}
			} else {
				Fonts.b12Full.renderCenter(chatMessage, screenX + overheadScreenX, screenY + overheadScreenY, 16776960, 0);
			}
		}
	}

	// TODO move somewhere else
	@OriginalMember(owner = "runetek4.client!rb", name = "a", descriptor = "(I)V")
	public static void resetTileOccupancy() {
		for (@Pc(7) int x = 0; x < 104; x++) {
			for (@Pc(14) int z = 0; z < 104; z++) {
				entityCountsPerTile[x][z] = 0;
			}
		}
	}

	//TODO move somewhere else
	@OriginalMember(owner = "client!cn", name = "b", descriptor = "(ZI)V")
	public static void pushPlayers(@OriginalArg(0) boolean arg0) {
		@Pc(3) int playerCount = PlayerList.playerCount;
		if (LoginManager.flagSceneTileX == PlayerList.self.xFine >> 7 && PlayerList.self.zFine >> 7 == LoginManager.flagSceneTileZ) {
			LoginManager.flagSceneTileX = 0;
		}
		if (arg0) {
			playerCount = 1;
		}
		@Pc(28) int playerIndex;
		@Pc(39) Player player;
		@Pc(82) int sceneZ;
		@Pc(182) int endX;
		@Pc(200) int endZ;
		@Pc(214) int x;
		@Pc(223) int z;
		@Pc(106) int local106;
		for (playerIndex = 0; playerIndex < playerCount; playerIndex++) {
			if (arg0) {
				player = PlayerList.self;
			} else {
				player = PlayerList.players[PlayerList.playerIds[playerIndex]];
			}
			if (player != null && player.isVisible()) {
				@Pc(55) int playerSize = player.getSize();
				@Pc(77) int sceneX;
				if (playerSize == 1) {
					if ((player.xFine & 0x7F) == 64 && (player.zFine & 0x7F) == 64) {
						sceneX = player.xFine >> 7;
						sceneZ = player.zFine >> 7;
						if (sceneX >= 0 && sceneX < 104 && sceneZ >= 0 && sceneZ < 104) {
							local106 = entityCountsPerTile[sceneX][sceneZ]++;
						}
					}
				} else if (((playerSize & 0x1) != 0 || (player.xFine & 0x7F) == 0 && (player.zFine & 0x7F) == 0) && ((playerSize & 0x1) != 1 || (player.xFine & 0x7F) == 64 && (player.zFine & 0x7F) == 64)) {
					sceneX = player.xFine - playerSize * 64 >> 7;
					sceneZ = player.zFine - playerSize * 64 >> 7;
					endX = player.getSize() + sceneX;
					if (endX > 104) {
						endX = 104;
					}
					if (sceneX < 0) {
						sceneX = 0;
					}
					endZ = sceneZ + player.getSize();
					if (sceneZ < 0) {
						sceneZ = 0;
					}
					if (endZ > 104) {
						endZ = 104;
					}
					for (x = sceneX; x < endX; x++) {
						for (z = sceneZ; z < endZ; z++) {
							local106 = entityCountsPerTile[x][z]++;
						}
					}
				}
			}
		}
		label220: for (playerIndex = 0; playerIndex < playerCount; playerIndex++) {
			@Pc(272) long entityBitset;
			if (arg0) {
				player = PlayerList.self;
				entityBitset = 8791798054912L;
			} else {
				player = PlayerList.players[PlayerList.playerIds[playerIndex]];
				entityBitset = (long) PlayerList.playerIds[playerIndex] << 32;
			}
			if (player != null && player.isVisible()) {
				player.lowMemory = false;
				if ((Preferences.manyIdleAnimations && PlayerList.playerCount > 200 || PlayerList.playerCount > 50) && !arg0 && player.movementSeqId == player.getBasType().readyanim) {
					player.lowMemory = true;
				}
				sceneZ = player.getSize();
				if (sceneZ == 1) {
					if ((player.xFine & 0x7F) == 64 && (player.zFine & 0x7F) == 64) {
						endX = player.xFine >> 7;
						endZ = player.zFine >> 7;
						if (endX < 0 || endX >= 104 || endZ < 0 || endZ >= 104) {
							continue;
						}
						if (entityCountsPerTile[endX][endZ] > 1) {
							local106 = entityCountsPerTile[endX][endZ]--;
							continue;
						}
					}
				} else if ((sceneZ & 0x1) == 0 && (player.xFine & 0x7F) == 0 && (player.zFine & 0x7F) == 0 || (sceneZ & 0x1) == 1 && (player.xFine & 0x7F) == 64 && (player.zFine & 0x7F) == 0) {
					endX = player.xFine - sceneZ * 64 >> 7;
					x = sceneZ + endX;
					endZ = player.zFine - sceneZ * 64 >> 7;
					if (x > 104) {
						x = 104;
					}
					if (endX < 0) {
						endX = 0;
					}
					z = sceneZ + endZ;
					if (endZ < 0) {
						endZ = 0;
					}
					@Pc(468) boolean local468 = true;
					if (z > 104) {
						z = 104;
					}
					@Pc(476) int local476;
					@Pc(485) int local485;
					for (local476 = endX; local476 < x; local476++) {
						for (local485 = endZ; local485 < z; local485++) {
							if (entityCountsPerTile[local476][local485] <= 1) {
								local468 = false;
								break;
							}
						}
					}
					if (local468) {
						local476 = endX;
						while (true) {
							if (local476 >= x) {
								continue label220;
							}
							for (local485 = endZ; local485 < z; local485++) {
								local106 = entityCountsPerTile[local476][local485]--;
							}
							local476++;
						}
					}
				}
				if (player.attachment == null || Client.loop < player.attachmentSetAt || player.attachmentResetAt <= Client.loop) {
					player.groundHeight = SceneGraph.getTileHeight(Player.plane, player.xFine, player.zFine);
					SceneGraph.addTemporary(Player.plane, player.xFine, player.zFine, player.groundHeight, (sceneZ - 1) * 64 + 60, player, player.orientation, entityBitset, player.seqStretches);
				} else {
					player.lowMemory = false;
					player.groundHeight = SceneGraph.getTileHeight(Player.plane, player.xFine, player.zFine);
					addTemporary(Player.plane, player.xFine, player.zFine, player.groundHeight, player, player.orientation, entityBitset, player.atachmentX0, player.attachmentZ0, player.attachmentX1, player.attachmentZ1);
				}
			}
		}
	}

	// TODO move somewhere else
	@OriginalMember(owner = "client!nk", name = "c", descriptor = "(IZ)V")
	public static void pushNpcs(@OriginalArg(1) boolean arg0) {
		@Pc(7) int npcIndex;
		@Pc(16) Npc npc;
		@Pc(107) int npcSize;
		@Pc(113) int sceneX;
		@Pc(133) int sceneZ;
		@Pc(149) int endX;
		@Pc(158) int endZ;
		@Pc(171) int local171;
		for (npcIndex = 0; npcIndex < NpcList.npcCount; npcIndex++) {
			npc = NpcList.npcs[NpcList.npcIds[npcIndex]];
			if (npc != null && npc.isVisible() && npc.type.drawabove == arg0 && npc.type.isMultiNpcValid()) {
				@Pc(42) int npcSize2 = npc.getSize();
				@Pc(97) int startX;
				if (npcSize2 == 1) {
					if ((npc.xFine & 0x7F) == 64 && (npc.zFine & 0x7F) == 64) {
						startX = npc.xFine >> 7;
						npcSize = npc.zFine >> 7;
						if (startX >= 0 && startX < 104 && npcSize >= 0 && npcSize < 104) {
							local171 = entityCountsPerTile[startX][npcSize]++;
						}
					}
				} else if (((npcSize2 & 0x1) != 0 || (npc.xFine & 0x7F) == 0 && (npc.zFine & 0x7F) == 0) && ((npcSize2 & 0x1) != 1 || (npc.xFine & 0x7F) == 64 && (npc.zFine & 0x7F) == 64)) {
					startX = npc.xFine - npcSize2 * 64 >> 7;
					npcSize = npc.zFine - npcSize2 * 64 >> 7;
					sceneX = npc.getSize() + startX;
					if (startX < 0) {
						startX = 0;
					}
					if (sceneX > 104) {
						sceneX = 104;
					}
					sceneZ = npcSize + npc.getSize();
					if (npcSize < 0) {
						npcSize = 0;
					}
					if (sceneZ > 104) {
						sceneZ = 104;
					}
					for (endX = startX; endX < sceneX; endX++) {
						for (endZ = npcSize; endZ < sceneZ; endZ++) {
							local171 = entityCountsPerTile[endX][endZ]++;
						}
					}
				}
			}
		}
		label200: for (npcIndex = 0; npcIndex < NpcList.npcCount; npcIndex++) {
			npc = NpcList.npcs[NpcList.npcIds[npcIndex]];
			@Pc(262) long entityBitset = (long) NpcList.npcIds[npcIndex] << 32 | 0x20000000L;
			if (npc != null && npc.isVisible() && npc.type.drawabove == arg0 && npc.type.isMultiNpcValid()) {
				npcSize = npc.getSize();
				if (npcSize == 1) {
					if ((npc.xFine & 0x7F) == 64 && (npc.zFine & 0x7F) == 64) {
						sceneX = npc.xFine >> 7;
						sceneZ = npc.zFine >> 7;
						if (sceneX < 0 || sceneX >= 104 || sceneZ < 0 || sceneZ >= 104) {
							continue;
						}
						if (entityCountsPerTile[sceneX][sceneZ] > 1) {
							local171 = entityCountsPerTile[sceneX][sceneZ]--;
							continue;
						}
					}
				} else if ((npcSize & 0x1) == 0 && (npc.xFine & 0x7F) == 0 && (npc.zFine & 0x7F) == 0 || (npcSize & 0x1) == 1 && (npc.xFine & 0x7F) == 64 && (npc.zFine & 0x7F) == 64) {
					sceneX = npc.xFine - npcSize * 64 >> 7;
					sceneZ = npc.zFine - npcSize * 64 >> 7;
					endZ = sceneZ + npcSize;
					if (sceneZ < 0) {
						sceneZ = 0;
					}
					@Pc(368) boolean local368 = true;
					endX = sceneX + npcSize;
					if (endZ > 104) {
						endZ = 104;
					}
					if (sceneX < 0) {
						sceneX = 0;
					}
					if (endX > 104) {
						endX = 104;
					}
					@Pc(396) int local396;
					@Pc(401) int local401;
					for (local396 = sceneX; local396 < endX; local396++) {
						for (local401 = sceneZ; local401 < endZ; local401++) {
							if (entityCountsPerTile[local396][local401] <= 1) {
								local368 = false;
								break;
							}
						}
					}
					if (local368) {
						local396 = sceneX;
						while (true) {
							if (local396 >= endX) {
								continue label200;
							}
							for (local401 = sceneZ; local401 < endZ; local401++) {
								local171 = entityCountsPerTile[local396][local401]--;
							}
							local396++;
						}
					}
				}
				if (!npc.type.active) {
					entityBitset |= Long.MIN_VALUE;
				}
				npc.groundHeight = SceneGraph.getTileHeight(Player.plane, npc.xFine, npc.zFine);
				SceneGraph.addTemporary(Player.plane, npc.xFine, npc.zFine, npc.groundHeight, npcSize * 64 + 60 - 64, npc, npc.orientation, entityBitset, npc.seqStretches);
			}
		}
	}

	//TODO move somewhere else
	@OriginalMember(owner = "client!pk", name = "i", descriptor = "(I)V")
	public static void updateSceneProjectiles() {
		for (@Pc(16) ProjAnimNode projectileNode = (ProjAnimNode) SceneGraph.projectiles.head(); projectileNode != null; projectileNode = (ProjAnimNode) SceneGraph.projectiles.next()) {
			@Pc(21) ProjectileAnimation projectile = projectileNode.value;
			if (Player.plane != projectile.currentPlane || projectile.lastCycle < Client.loop) {
				projectileNode.unlink();
			} else if (Client.loop >= projectile.firstCycle) {
				if (projectile.target > 0) {
					@Pc(54) Npc npc = NpcList.npcs[projectile.target - 1];
					if (npc != null && npc.xFine >= 0 && npc.xFine < 13312 && npc.zFine >= 0 && npc.zFine < 13312) {
						projectile.setTarget(npc.zFine, Client.loop, SceneGraph.getTileHeight(projectile.currentPlane, npc.xFine, npc.zFine) - projectile.baseZ, npc.xFine);
					}
				}
				if (projectile.target < 0) {
					@Pc(102) int playerIndex = -projectile.target - 1;
					@Pc(107) Player player;
					if (PlayerList.selfId == playerIndex) {
						player = PlayerList.self;
					} else {
						player = PlayerList.players[playerIndex];
					}
					if (player != null && player.xFine >= 0 && player.xFine < 13312 && player.zFine >= 0 && player.zFine < 13312) {
						projectile.setTarget(player.zFine, Client.loop, SceneGraph.getTileHeight(projectile.currentPlane, player.xFine, player.zFine) - projectile.baseZ, player.xFine);
					}
				}
				projectile.update(Protocol.sceneDelta);
				SceneGraph.addTemporary(Player.plane, (int) projectile.x, (int) projectile.y, (int) projectile.z, 60, projectile, projectile.yaw, -1L, false);
			}
		}
	}

	//TODO move somewhere else
	@OriginalMember(owner = "client!u", name = "a", descriptor = "(Z)V")
	public static void updateSpotAnims() {
		for (@Pc(9) SpotAnimEntity spotAnimNode = (SpotAnimEntity) SceneGraph.spotanims.head(); spotAnimNode != null; spotAnimNode = (SpotAnimEntity) SceneGraph.spotanims.next()) {
			@Pc(15) SpotAnim animation = spotAnimNode.animation;
			if (animation.level != Player.plane || animation.seqComplete) {
				spotAnimNode.unlink();
			} else if (animation.startCycle <= Client.loop) {
				animation.update(Protocol.sceneDelta);
				if (animation.seqComplete) {
					spotAnimNode.unlink();
				} else {
					SceneGraph.addTemporary(animation.level, animation.x, animation.z, animation.y, 60, animation, 0, -1L, false);
				}
			}
		}
	}

	//TODO move somewhere else
	@OriginalMember(owner = "runetek4.client!tc", name = "a", descriptor = "(B)I")
	public static int getRoofVisibilityMode() {
		if (neverRemoveRoofs) {
			return 0;
		} else if (SceneGraph.allLevelsAreVisible()) {
			return Preferences.roofsVisible ? 2 : 1;
		} else {
			return 1;
		}
	}

	@OriginalMember(owner = "runetek4.client!ok", name = "a", descriptor = "(IIB)Lclient!ce;")
	public static SecondaryLinkedList method3333(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(9) SecondaryLinkedList local9 = new SecondaryLinkedList();
		for (@Pc(14) Map local14 = (Map) MapList.areas.head(); local14 != null; local14 = (Map) MapList.areas.next()) {
			if (local14.valid && local14.containsSource(arg1, arg0)) {
				local9.addTail(local14);
			}
		}
		return local9;
	}

	@OriginalMember(owner = "client!cn", name = "a", descriptor = "(BB)V")
	public static void method960(@OriginalArg(0) byte arg0) {
		if (tileMarkings == null) {
			tileMarkings = new byte[4][104][104];
		}
		for (@Pc(20) int local20 = 0; local20 < 4; local20++) {
			for (@Pc(25) int local25 = 0; local25 < 104; local25++) {
				for (@Pc(32) int local32 = 0; local32 < 104; local32++) {
					tileMarkings[local20][local25][local32] = arg0;
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!sm", name = "a", descriptor = "(II)V")
	public static void method3993(@OriginalArg(0) int arg0) {
		anIntArray338 = new int[arg0];
		anIntArray518 = new int[arg0];
		anIntArray476 = new int[arg0];
		anIntArray134 = new int[arg0];
		maxHeights = new int[arg0];
	}

	@OriginalMember(owner = "runetek4.client!ke", name = "f", descriptor = "(B)V")
	public static void method2608() {
		@Pc(7) int local7 = 0;
		for (@Pc(23) int local23 = 0; local23 < 104; local23++) {
			for (@Pc(30) int local30 = 0; local30 < 104; local30++) {
				if (calculateVisibleRegion(true, local23, local30, SceneGraph.tiles, local7)) {
					local7++;
				}
				if (local7 >= 512) {
					return;
				}
			}
		}
	}

	// TODO move somewhere else
	@OriginalMember(owner = "runetek4.client!uj", name = "a", descriptor = "(BZII[[[Lclient!bj;I)Z")
	public static boolean calculateVisibleRegion(@OriginalArg(1) boolean forceVisible, @OriginalArg(2) int startX, @OriginalArg(3) int startZ, @OriginalArg(4) Tile[][][] tiles, @OriginalArg(5) int regionIndex) {
		@Pc(14) byte markValue = forceVisible ? 1 : (byte) (anInt3325 & 0xFF);
		if (markValue == tileMarkings[Player.plane][startX][startZ]) {
			return false;
		} else if ((SceneGraph.renderFlags[Player.plane][startX][startZ] & 0x4) == 0) {
			return false;
		} else {
			@Pc(47) int queueRead = 0;
			@Pc(49) byte queueStart = 0;
			PathFinder.bfsStepX[0] = startX;
			@Pc(69) int queueWrite = queueStart + 1;
			PathFinder.bfsStepZ[0] = startZ;
			tileMarkings[Player.plane][startX][startZ] = markValue;
			while (queueRead != queueWrite) {
				@Pc(94) int direction1 = PathFinder.bfsStepX[queueRead] >> 16 & 0xFF;
				@Pc(102) int direction2 = PathFinder.bfsStepX[queueRead] >> 24 & 0xFF;
				@Pc(108) int currentX = PathFinder.bfsStepX[queueRead] & 0xFFFF;
				@Pc(116) int direction3 = PathFinder.bfsStepZ[queueRead] >> 16 & 0xFF;
				@Pc(122) int currentZ = PathFinder.bfsStepZ[queueRead] & 0xFFFF;
				queueRead = queueRead + 1 & 0xFFF;
				@Pc(130) boolean isBlocked = false;
				@Pc(132) boolean foundVisible = false;
				if ((SceneGraph.renderFlags[Player.plane][currentX][currentZ] & 0x4) == 0) {
					isBlocked = true;
				}
				@Pc(150) int level;
				@Pc(191) int local191;
				label238: for (level = Player.plane + 1; level <= 3; level++) {
					if ((SceneGraph.renderFlags[level][currentX][currentZ] & 0x8) == 0) {
						@Pc(227) int sceneType;
						@Pc(358) int local358;
						if (isBlocked && tiles[level][currentX][currentZ] != null) {
							if (tiles[level][currentX][currentZ].wall != null) {
								local191 = SceneGraph.getCollisionFlag(direction1);
								if (tiles[level][currentX][currentZ].wall.typeA == local191 || tiles[level][currentX][currentZ].wall.typeB == local191) {
									continue;
								}
								if (direction2 != 0) {
									sceneType = SceneGraph.getCollisionFlag(direction2);
									if (sceneType == tiles[level][currentX][currentZ].wall.typeA || tiles[level][currentX][currentZ].wall.typeB == sceneType) {
										continue;
									}
								}
								if (direction3 != 0) {
									sceneType = SceneGraph.getCollisionFlag(direction3);
									if (sceneType == tiles[level][currentX][currentZ].wall.typeA || sceneType == tiles[level][currentX][currentZ].wall.typeB) {
										continue;
									}
								}
							}
							if (tiles[level][currentX][currentZ].scenery != null) {
								for (local191 = 0; local191 < tiles[level][currentX][currentZ].sceneryLen; local191++) {
									sceneType = (int) (tiles[level][currentX][currentZ].scenery[local191].key >> 14 & 0x3FL);
									if (sceneType == 21) {
										sceneType = 19;
									}
									@Pc(352) int rotation = (int) (tiles[level][currentX][currentZ].scenery[local191].key >> 20 & 0x3L);
									local358 = sceneType | rotation << 6;
									if (local358 == direction1 || direction2 != 0 && local358 == direction2 || direction3 != 0 && direction3 == local358) {
										continue label238;
									}
								}
							}
						}
						foundVisible = true;
						@Pc(395) Tile tile = tiles[level][currentX][currentZ];
						if (tile != null && tile.sceneryLen > 0) {
							for (sceneType = 0; sceneType < tile.sceneryLen; sceneType++) {
								@Pc(418) Scenery scenery = tile.scenery[sceneType];
								if (scenery.xMax != scenery.xMin || scenery.zMax != scenery.zMin) {
									for (local358 = scenery.xMin; local358 <= scenery.xMax; local358++) {
										for (@Pc(450) int z = scenery.zMin; z <= scenery.zMax; z++) {
											tileMarkings[level][local358][z] = markValue;
										}
									}
								}
							}
						}
						tileMarkings[level][currentX][currentZ] = markValue;
					}
				}
				if (foundVisible) {
					if (SceneGraph.tileHeights[Player.plane + 1][currentX][currentZ] > maxHeights[regionIndex]) {
						maxHeights[regionIndex] = SceneGraph.tileHeights[Player.plane + 1][currentX][currentZ];
					}
					level = currentX << 7;
					if (level < anIntArray338[regionIndex]) {
						anIntArray338[regionIndex] = level;
					} else if (anIntArray518[regionIndex] < level) {
						anIntArray518[regionIndex] = level;
					}
					local191 = currentZ << 7;
					if (anIntArray476[regionIndex] > local191) {
						anIntArray476[regionIndex] = local191;
					} else if (anIntArray134[regionIndex] < local191) {
						anIntArray134[regionIndex] = local191;
					}
				}
				if (!isBlocked) {
					if (currentX >= 1 && tileMarkings[Player.plane][currentX - 1][currentZ] != markValue) {
						PathFinder.bfsStepX[queueWrite] = currentX - 1 | 0x120000 | 0xD3000000;
						PathFinder.bfsStepZ[queueWrite] = currentZ | 0x130000;
						queueWrite = queueWrite + 1 & 0xFFF;
						tileMarkings[Player.plane][currentX - 1][currentZ] = markValue;
					}
					currentZ++;
					if (currentZ < 104) {
						if (currentX - 1 >= 0 && markValue != tileMarkings[Player.plane][currentX - 1][currentZ] && (SceneGraph.renderFlags[Player.plane][currentX][currentZ] & 0x4) == 0 && (SceneGraph.renderFlags[Player.plane][currentX - 1][currentZ - 1] & 0x4) == 0) {
							PathFinder.bfsStepX[queueWrite] = 0x52000000 | 0x120000 | currentX - 1;
							PathFinder.bfsStepZ[queueWrite] = currentZ | 0x130000;
							tileMarkings[Player.plane][currentX - 1][currentZ] = markValue;
							queueWrite = queueWrite + 1 & 0xFFF;
						}
						if (markValue != tileMarkings[Player.plane][currentX][currentZ]) {
							PathFinder.bfsStepX[queueWrite] = currentX | 0x13000000 | 0x520000;
							PathFinder.bfsStepZ[queueWrite] = currentZ | 0x530000;
							queueWrite = queueWrite + 1 & 0xFFF;
							tileMarkings[Player.plane][currentX][currentZ] = markValue;
						}
						if (currentX + 1 < 104 && tileMarkings[Player.plane][currentX + 1][currentZ] != markValue && (SceneGraph.renderFlags[Player.plane][currentX][currentZ] & 0x4) == 0 && (SceneGraph.renderFlags[Player.plane][currentX + 1][currentZ - 1] & 0x4) == 0) {
							PathFinder.bfsStepX[queueWrite] = 0x92000000 | 0x520000 | currentX + 1;
							PathFinder.bfsStepZ[queueWrite] = currentZ | 0x530000;
							tileMarkings[Player.plane][currentX + 1][currentZ] = markValue;
							queueWrite = queueWrite + 1 & 0xFFF;
						}
					}
					currentZ--;
					if (currentX + 1 < 104 && markValue != tileMarkings[Player.plane][currentX + 1][currentZ]) {
						PathFinder.bfsStepX[queueWrite] = currentX + 1 | 0x920000 | 0x53000000;
						PathFinder.bfsStepZ[queueWrite] = currentZ | 0x930000;
						tileMarkings[Player.plane][currentX + 1][currentZ] = markValue;
						queueWrite = queueWrite + 1 & 0xFFF;
					}
					currentZ--;
					if (currentZ >= 0) {
						if (currentX - 1 >= 0 && tileMarkings[Player.plane][currentX - 1][currentZ] != markValue && (SceneGraph.renderFlags[Player.plane][currentX][currentZ] & 0x4) == 0 && (SceneGraph.renderFlags[Player.plane][currentX - 1][currentZ + 1] & 0x4) == 0) {
							PathFinder.bfsStepX[queueWrite] = currentX - 1 | 0xD20000 | 0x12000000;
							PathFinder.bfsStepZ[queueWrite] = currentZ | 0xD30000;
							tileMarkings[Player.plane][currentX - 1][currentZ] = markValue;
							queueWrite = queueWrite + 1 & 0xFFF;
						}
						if (markValue != tileMarkings[Player.plane][currentX][currentZ]) {
							PathFinder.bfsStepX[queueWrite] = currentX | 0xD20000 | 0x93000000;
							PathFinder.bfsStepZ[queueWrite] = currentZ | 0xD30000;
							queueWrite = queueWrite + 1 & 0xFFF;
							tileMarkings[Player.plane][currentX][currentZ] = markValue;
						}
						if (currentX + 1 < 104 && tileMarkings[Player.plane][currentX + 1][currentZ] != markValue && (SceneGraph.renderFlags[Player.plane][currentX][currentZ] & 0x4) == 0 && (SceneGraph.renderFlags[Player.plane][currentX + 1][currentZ + 1] & 0x4) == 0) {
							PathFinder.bfsStepX[queueWrite] = currentX + 1 | 0xD2000000 | 0x920000;
							PathFinder.bfsStepZ[queueWrite] = currentZ | 0x930000;
							tileMarkings[Player.plane][currentX + 1][currentZ] = markValue;
							queueWrite = queueWrite + 1 & 0xFFF;
						}
					}
				}
			}
			if (maxHeights[regionIndex] != -1000000) {
				maxHeights[regionIndex] += 10;
				anIntArray338[regionIndex] -= 50;
				anIntArray518[regionIndex] += 50;
				anIntArray134[regionIndex] += 50;
				anIntArray476[regionIndex] -= 50;
			}
			return true;
		}
	}

	@OriginalMember(owner = "runetek4.client!nf", name = "a", descriptor = "(Lclient!na;BZ)V")
	public static void openUrl(@OriginalArg(0) JString arg0, @OriginalArg(2) boolean arg1) {
	// TODO Implement a replacement for this method??

//		if (!arg1) {
//			try {
//				GameShell.instance.getAppletContext().showDocument(arg0.method3127(GameShell.instance.getCodeBase()), "_top");
//			} catch (@Pc(22) Exception local22) {
//			}
//			return;
//		}
//		if (GlRenderer.enabled && GameShell.openWindowJavaScript) {
//			try {
//				BrowserControl.call(GameShell.signLink.applet, "openjs", new Object[] { arg0.method3127(GameShell.instance.getCodeBase()).toString() });
//				return;
//			} catch (@Pc(48) Throwable local48) {
//			}
//		}
//		try {
//			GameShell.instance.getAppletContext().showDocument(arg0.method3127(GameShell.instance.getCodeBase()), "_blank");
//		} catch (@Pc(59) Exception local59) {
//		}
	}

	//TODO move somewhere else
	@OriginalMember(owner = "runetek4.client!og", name = "a", descriptor = "(BIILclient!fe;III)V")
	public static void setOverheadScreenCoordinateOffsets(@OriginalArg(1) int screenHalfWidth, @OriginalArg(2) int cameraX, @OriginalArg(3) PathingEntity entity, @OriginalArg(4) int cameraZ, @OriginalArg(5) int heightOffset, @OriginalArg(6) int screenHalfHeight) {
		calculateScreenCoordinates(screenHalfHeight, cameraX, entity.zFine, heightOffset, screenHalfWidth, entity.xFine, cameraZ);
	}

	//TODO move somewhere else
	@OriginalMember(owner = "client!ed", name = "b", descriptor = "(II)Lclient!ba;")
	public static World getWorld(@OriginalArg(1) int arg0) {
		return WorldList.loaded && arg0 >= WorldList.minId && arg0 <= WorldList.maxId ? WorldList.worlds[arg0 - WorldList.minId] : null;
	}

	//TODO move somewhere else
	@OriginalMember(owner = "runetek4.client!sc", name = "a", descriptor = "()V")
	public static void clearAllScenery() {
		for (@Pc(1) int i = 0; i < SceneGraph.sceneryLen; i++) {
			@Pc(8) Scenery scenery = SceneGraph.scenery[i];
			SceneGraph.removeScenery(scenery);
			SceneGraph.scenery[i] = null;
		}
		SceneGraph.sceneryLen = 0;
	}

	//TODO move somewhere else
	@OriginalMember(owner = "client!be", name = "a", descriptor = "(Z)Lclient!na;")
	public static JString buildConfigUrl() {
		@Pc(8) JString modeString = aClass100_518;
		@Pc(10) JString settingsParam = JString.EMPTY;
		if (Client.modeWhere != 0) {
			modeString = aClass100_365;
		}
		if (Client.settings != null) {
			settingsParam = JString.concatenate(new JString[] {aClass100_687, Client.settings});
		}
		return JString.concatenate(new JString[] {aClass100_424, modeString, aClass100_886, JString.parseInt(Client.language), aClass100_98, JString.parseInt(Client.affiliate), settingsParam, aClass100_268});
	}

	//TODO move somewhere else
	@OriginalMember(owner = "runetek4.client!ol", name = "a", descriptor = "(IIIILclient!th;IJIIII)Z")
	public static boolean addTemporary(@OriginalArg(0) int level, @OriginalArg(1) int x, @OriginalArg(2) int z, @OriginalArg(3) int height, @OriginalArg(4) Entity entity, @OriginalArg(5) int orientation, @OriginalArg(6) long entityId, @OriginalArg(7) int attachmentX0, @OriginalArg(8) int attachmentZ0, @OriginalArg(9) int attachmentX1, @OriginalArg(10) int attachmentZ1) {
		return entity == null ? true : SceneGraph.addLoc(level, attachmentX0, attachmentZ0, attachmentX1 + 1 - attachmentX0, attachmentZ1 - attachmentZ0 + 1, x, z, height, entity, orientation, true, entityId);
	}

	//TODO move somewhere else
	@OriginalMember(owner = "client!uh", name = "f", descriptor = "(I)V")
	public static void performVisibilityCulling() {
		if (getRoofVisibilityMode() != 2) {
			return;
		}
		@Pc(27) byte markValue = (byte) (anInt3325 - 4 & 0xFF);
		@Pc(31) int stripIndex = anInt3325 % 104;
		@Pc(33) int local33;
		@Pc(40) int local40;
		for (local33 = 0; local33 < 4; local33++) {
			for (local40 = 0; local40 < 104; local40++) {
				tileMarkings[local33][stripIndex][local40] = markValue;
			}
		}
		if (Player.plane == 3) {
			return;
		}
		for (local33 = 0; local33 < 2; local33++) {
			maxHeights[local33] = -1000000;
			anIntArray338[local33] = 1000000;
			anIntArray518[local33] = 0;
			anIntArray476[local33] = 1000000;
			anIntArray134[local33] = 0;
		}
		if (Camera.cameraType != 1) {
			local33 = SceneGraph.getTileHeight(Player.plane, Camera.renderX, Camera.renderZ);
			if (local33 - Camera.cameraY < 800 && (SceneGraph.renderFlags[Player.plane][Camera.renderX >> 7][Camera.renderZ >> 7] & 0x4) != 0) {
				calculateVisibleRegion(false, Camera.renderX >> 7, Camera.renderZ >> 7, SceneGraph.tiles, 1);
			}
			return;
		}
		if ((SceneGraph.renderFlags[Player.plane][PlayerList.self.xFine >> 7][PlayerList.self.zFine >> 7] & 0x4) != 0) {
			calculateVisibleRegion(false, PlayerList.self.xFine >> 7, PlayerList.self.zFine >> 7, SceneGraph.tiles, 0);
		}
		if (Camera.cameraPitch >= 310) {
			return;
		}
		@Pc(135) int playerZ = PlayerList.self.zFine >> 7;
		local40 = Camera.renderZ >> 7;
		@Pc(146) int deltaZ;
		if (local40 < playerZ) {
			deltaZ = playerZ - local40;
		} else {
			deltaZ = local40 - playerZ;
		}
		local33 = Camera.renderX >> 7;
		@Pc(162) int playerX = PlayerList.self.xFine >> 7;
		@Pc(174) int deltaX;
		if (playerX > local33) {
			deltaX = playerX - local33;
		} else {
			deltaX = local33 - playerX;
		}
		@Pc(192) int local192;
		@Pc(186) int local186;
		if (deltaX <= deltaZ) {
			local186 = 32768;
			local192 = deltaX * 65536 / deltaZ;
			while (local40 != playerZ) {
				if (local40 < playerZ) {
					local40++;
				} else if (local40 > playerZ) {
					local40--;
				}
				if ((SceneGraph.renderFlags[Player.plane][local33][local40] & 0x4) != 0) {
					calculateVisibleRegion(false, local33, local40, SceneGraph.tiles, 1);
					break;
				}
				local186 += local192;
				if (local186 >= 65536) {
					if (playerX > local33) {
						local33++;
					} else if (playerX < local33) {
						local33--;
					}
					local186 -= 65536;
					if ((SceneGraph.renderFlags[Player.plane][local33][local40] & 0x4) != 0) {
						calculateVisibleRegion(false, local33, local40, SceneGraph.tiles, 1);
						break;
					}
				}
			}
			return;
		}
		local186 = 32768;
		local192 = deltaZ * 65536 / deltaX;
		while (playerX != local33) {
			if (playerX > local33) {
				local33++;
			} else if (local33 > playerX) {
				local33--;
			}
			if ((SceneGraph.renderFlags[Player.plane][local33][local40] & 0x4) != 0) {
				calculateVisibleRegion(false, local33, local40, SceneGraph.tiles, 1);
				break;
			}
			local186 += local192;
			if (local186 >= 65536) {
				if (local40 < playerZ) {
					local40++;
				} else if (playerZ < local40) {
					local40--;
				}
				local186 -= 65536;
				if ((SceneGraph.renderFlags[Player.plane][local33][local40] & 0x4) != 0) {
					calculateVisibleRegion(false, local33, local40, SceneGraph.tiles, 1);
					break;
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!lf", name = "a", descriptor = "(I)V")
	public static void method2742() {
		if (Client.gameState == 10 && GlRenderer.enabled) {
			Client.processGameStatus(28);
		}
		if (Client.gameState == 30) {
			Client.processGameStatus(25);
		}
	}

	//TODO move somewhere else
	@OriginalMember(owner = "runetek4.client!sf", name = "b", descriptor = "(B)V")
	public static void determineMenuSize() {
		@Pc(16) int maxWidth = Fonts.b12Full.getStringWidth(LocalizedText.CHOOSE_OPTION);
		@Pc(18) int menuHeight;
		@Pc(27) int local27;
		for (menuHeight = 0; menuHeight < MiniMenu.menuActionRow; menuHeight++) {
			local27 = Fonts.b12Full.getStringWidth(MiniMenu.getOp(menuHeight));
			if (local27 > maxWidth) {
				maxWidth = local27;
			}
		}
		menuHeight = MiniMenu.menuActionRow * 15 + 21;
		@Pc(43) int menuY = scriptMouseY;
		maxWidth += 8;
		local27 = scriptMouseX - maxWidth / 2;
		if (menuY + menuHeight > GameShell.canvasHeigth) {
			menuY = GameShell.canvasHeigth - menuHeight;
		}
		if (GameShell.canvasWidth < local27 + maxWidth) {
			local27 = GameShell.canvasWidth - maxWidth;
		}
		if (local27 < 0) {
			local27 = 0;
		}
		if (menuY < 0) {
			menuY = 0;
		}
		if (MiniMenu.menuState == 1) {
			if (scriptMouseX == Mouse.lastClickX && Mouse.lastClickY == scriptMouseY) {
				ComponentList.menuHeight = MiniMenu.menuActionRow * 15 + (ComponentList.hasScrollbar ? 26 : 22);
				MiniMenu.menuState = 0;
				ComponentList.menuY = menuY;
				ComponentList.menuX = local27;
				menuVisible = true;
				ComponentList.menuWidth = maxWidth;
			}
		} else if (scriptMouseX == Mouse.mouseClickX && scriptMouseY == Mouse.mouseClickY) {
			ComponentList.menuX = local27;
			MiniMenu.menuState = 0;
			ComponentList.menuWidth = maxWidth;
			ComponentList.menuY = menuY;
			ComponentList.menuHeight = (ComponentList.hasScrollbar ? 26 : 22) + MiniMenu.menuActionRow * 15;
			menuVisible = true;
		} else {
			Mouse.lastClickY = Mouse.mouseClickY;
			Mouse.lastClickX = Mouse.mouseClickX;
			MiniMenu.menuState = 1;
		}
	}


	//TODO move somewhere else
	@OriginalMember(owner = "client!ag", name = "a", descriptor = "(IIIIIIIII)V")
	public static void renderOrInvalidateComponent(@OriginalArg(1) int widgetId, @OriginalArg(2) int x, @OriginalArg(3) int y, @OriginalArg(4) int width, @OriginalArg(5) int invalidateId, @OriginalArg(6) int height, @OriginalArg(7) int clipX, @OriginalArg(8) int clipY) {
		if (ComponentList.load(widgetId)) {
			ComponentList.renderComponent(x, clipY, width, ComponentList.cachedComponents[widgetId], y, -1, clipX, height, invalidateId);
		} else if (invalidateId == -1) {
			for (@Pc(27) int i = 0; i < 100; i++) {
				ComponentList.componentNeedsRedraw[i] = true;
			}
		} else {
			ComponentList.componentNeedsRedraw[invalidateId] = true;
		}
	}

	//TODO move somewhere else
	@OriginalMember(owner = "client!da", name = "a", descriptor = "(IIILclient!be;)V")
	public static void startComponentDrag(@OriginalArg(0) int startY, @OriginalArg(1) int startX, @OriginalArg(3) Component component) {
		if (dragComponent != null || menuVisible || (component == null || getWidgetContainer(component) == null)) {
			return;
		}
		dragComponent = component;
		containerComponent = getWidgetContainer(component);
		dragStartX = startX;
		isDragging = false;
		dragTime = 0;
		dragStartY = startY;
	}

	//TODO move somewhere else
	@OriginalMember(owner = "client!ha", name = "a", descriptor = "(ILclient!be;)Lclient!be;")
	public static Component getWidgetContainer(@OriginalArg(1) Component component) {
		@Pc(12) Component container = ComponentList.canAcceptDrop(component);
		if (container == null) {
			container = component.parent;
		}
		return container;
	}
	
	/**
	 * Highlights the tile the player is currently on with a red border in software rendering mode
	 */
	public static void highlightAllTiles() {
		// Only render in software mode, not GL mode
		if (GlRenderer.enabled) {
			return;
		}
		
		// Only highlight the player's current tile
		if (PlayerList.self != null) {
			int playerTileX = PlayerList.self.xFine >> 7;  // Convert fine coordinates to tile coordinates
			int playerTileZ = PlayerList.self.zFine >> 7;  // Convert fine coordinates to tile coordinates
			int currentPlane = Player.plane;
			
			drawTileBorder(currentPlane, playerTileX, playerTileZ);
		}
	}
	
	/**
	 * Highlights the tile the cursor is hovering over with a blue border in software rendering mode
	 */
	public static void highlightHoveredTile() {
		highlightHoveredTile(0, 0); // Default viewport offset
	}
	
	/**
	 * Highlights the tile the cursor is hovering over using calculateScreenCoordinates
	 */
	public static void highlightHoveredTile(int viewportX, int viewportY) {
		// Only render in software mode, not GL mode
		if (GlRenderer.enabled) {
			return;
		}
		
		// Get raw mouse coordinates 
		int mouseX = Mouse.lastMouseX;
		int mouseY = Mouse.lastMouseY;
		
		int hoveredTileX = -1;
		int hoveredTileZ = -1;
		int currentPlane = Player.plane;
		
		// Check tiles around the player (reasonable search area)
		int playerTileX = PlayerList.self != null ? PlayerList.self.xFine >> 7 : 52;
		int playerTileZ = PlayerList.self != null ? PlayerList.self.zFine >> 7 : 52;
		
		double closestDistance = Double.MAX_VALUE;
		
		// Search in a reasonable area around the player
		for (int checkX = Math.max(0, playerTileX - 20); checkX < Math.min(104, playerTileX + 20); checkX++) {
			for (int checkZ = Math.max(0, playerTileZ - 20); checkZ < Math.min(104, playerTileZ + 20); checkZ++) {
				// Get tile center coordinates
				int worldX = checkX * 128 + 64;  // Center of tile
				int worldZ = checkZ * 128 + 64;  // Center of tile
				int worldY = SceneGraph.getTileHeight(currentPlane, worldX, worldZ);
				
				// Use the same calculateScreenCoordinates function we know works for overheads
				calculateScreenCoordinates(GameShell.canvasWidth / 2, 256, worldZ, worldY, GameShell.canvasHeigth / 2, worldX, 256);
				
				if (overheadScreenX != -1 && overheadScreenY != -1) {
					// Calculate distance from mouse to tile center on screen
					double distance = Math.sqrt((mouseX - overheadScreenX) * (mouseX - overheadScreenX) + (mouseY - overheadScreenY) * (mouseY - overheadScreenY));
					
					// If this is the closest tile to the mouse cursor so far
					if (distance < closestDistance && distance < 100) { // Within reasonable distance
						closestDistance = distance;
						hoveredTileX = checkX;
						hoveredTileZ = checkZ;
					}
				}
			}
		}
		
		// Draw border for the hovered tile if found
		if (hoveredTileX != -1 && hoveredTileZ != -1) {
			drawTileBorderWithColor(currentPlane, hoveredTileX, hoveredTileZ, 0x0000FF); // Blue color
		}
	}
	
	/**
	 * Draws a red border around a specific tile using its actual 3D geometry
	 */
	private static void drawTileBorder(int plane, int tileX, int tileZ) {
		drawTileBorderWithColor(plane, tileX, tileZ, 0xFF0000); // Red color
	}
	
	/**
	 * Draws a colored border around a specific tile using its actual 3D geometry
	 */
	private static void drawTileBorderWithColor(int plane, int tileX, int tileZ, int color) {
		// Get the tile from SceneGraph
		if (SceneGraph.tiles == null || SceneGraph.tiles[plane] == null) {
			return;
		}
		
		Tile tile = SceneGraph.tiles[plane][tileX][tileZ];
		if (tile == null) {
			return;
		}
		
		// Use either the shaped tile or fall back to calculating tile corners
		if (tile.shapedTile != null) {
			drawShapedTileBorderWithColor(tile.shapedTile, tileX, tileZ, plane, color);
		} else {
			drawSimpleTileBorderWithColor(tileX, tileZ, plane, color);
		}
	}
	
	/**
	 * Draws border for a shaped tile using its actual vertex data
	 */
	private static void drawShapedTileBorder(ShapedTile shapedTile, int tileX, int tileZ, int plane) {
		drawShapedTileBorderWithColor(shapedTile, tileX, tileZ, plane, 0xFF0000); // Red color
	}
	
	/**
	 * Draws border for a shaped tile using its actual vertex data with custom color
	 */
	private static void drawShapedTileBorderWithColor(ShapedTile shapedTile, int tileX, int tileZ, int plane, int color) {
		int[] vertexX = shapedTile.vertexX;  // Already in world coordinates
		int[] vertexY = shapedTile.vertexY;  // Already in world coordinates (height)
		int[] vertexZ = shapedTile.vertexZ;  // Already in world coordinates
		
		if (vertexX == null || vertexY == null || vertexZ == null) {
			return;
		}
		
		int vertexCount = vertexX.length;
		
		// Project all vertices to screen coordinates using exact same method as drawTileOverlay
		int[] screenX = new int[vertexCount];
		int[] screenY = new int[vertexCount];
		int validVertices = 0;
		
		// Use exact same transformation as drawTileOverlay - vertices are already in world coordinates
		for (int i = 0; i < vertexCount; i++) {
			// Use exact same variable names and assignments as drawTileOverlay
			int vertexA = vertexX[i] - SceneGraph.eyeX;      // Same as line 4330
			int relativeY = vertexY[i] - SceneGraph.eyeY;    // Same as line 4331
			int local29 = vertexZ[i] - SceneGraph.eyeZ;      // Same as line 4332
			
			// Use the same pre-calculated sin/cos values that drawTileOverlay receives
			int sinYaw = SceneGraph.sinYaw;    // MathUtils.sin[yaw] from setupSceneRender
			int cosYaw = SceneGraph.cosYaw;    // MathUtils.cos[yaw] from setupSceneRender
			int sinPitch = SceneGraph.sinPitch;  // MathUtils.sin[pitch] from setupSceneRender
			int cosPitch = SceneGraph.cosPitch;  // MathUtils.cos[pitch] from setupSceneRender
			
			int local39 = local29 * sinPitch + vertexA * cosPitch >> 16;         // Same as line 4333
			int rotatedZ = local29 * cosPitch - vertexA * sinPitch >> 16;        // Same as line 4334
			int transformedY = relativeY * cosYaw - rotatedZ * sinYaw >> 16;     // Same as line 4335
			int depth = relativeY * sinYaw + rotatedZ * cosYaw >> 16;            // Same as line 4336
			
			if (depth >= 50) {
				screenX[validVertices] = Rasterizer.centerX + (local39 << 9) / depth;        // Same as line 4345
				screenY[validVertices] = Rasterizer.centerY + (transformedY << 9) / depth;   // Same as line 4346
				validVertices++;
			}
		}
		
		// Draw lines between consecutive vertices to form the tile border
		if (validVertices >= 3) {
			for (int i = 0; i < validVertices; i++) {
				int nextIndex = (i + 1) % validVertices;
				SoftwareRenderer.drawDiagonalLine(screenX[i], screenY[i], screenX[nextIndex], screenY[nextIndex], color);
			}
		}
	}
	
	/**
	 * Draws a simple rectangular border for tiles without shaped geometry
	 */
	private static void drawSimpleTileBorder(int tileX, int tileZ, int plane) {
		drawSimpleTileBorderWithColor(tileX, tileZ, plane, 0xFF0000); // Red color
	}
	
	/**
	 * Draws a simple rectangular border for tiles without shaped geometry with custom color
	 */
	private static void drawSimpleTileBorderWithColor(int tileX, int tileZ, int plane, int color) {
		int tileSize = 128;
		
		// Calculate world coordinates for tile corners (matching how ShapedTile constructor works)
		int worldBaseX = tileX * tileSize;
		int worldBaseZ = tileZ * tileSize;
		
		// Get heights at each corner using same method as ShapedTile
		int swHeight = SceneGraph.getTileHeight(plane, worldBaseX, worldBaseZ);
		int seHeight = SceneGraph.getTileHeight(plane, worldBaseX + tileSize, worldBaseZ);
		int nwHeight = SceneGraph.getTileHeight(plane, worldBaseX, worldBaseZ + tileSize);
		int neHeight = SceneGraph.getTileHeight(plane, worldBaseX + tileSize, worldBaseZ + tileSize);
		
		// Create corner vertices in world coordinates (same as ShapedTile vertex layout)
		int[] cornerX = {worldBaseX, worldBaseX + tileSize, worldBaseX, worldBaseX + tileSize};
		int[] cornerZ = {worldBaseZ, worldBaseZ, worldBaseZ + tileSize, worldBaseZ + tileSize};
		int[] cornerY = {swHeight, seHeight, nwHeight, neHeight};
		int[] screenX = new int[4];
		int[] screenY = new int[4];
		int validCorners = 0;
		
		// Project corners using exact same transformation as drawTileOverlay
		for (int i = 0; i < 4; i++) {
			// Use exact same variable names and assignments as drawTileOverlay
			int vertexA = cornerX[i] - SceneGraph.eyeX;      // Same as line 4330
			int relativeY = cornerY[i] - SceneGraph.eyeY;    // Same as line 4331
			int local29 = cornerZ[i] - SceneGraph.eyeZ;      // Same as line 4332
			
			// Use the same pre-calculated sin/cos values that drawTileOverlay receives
			int sinYaw = SceneGraph.sinYaw;    // MathUtils.sin[yaw] from setupSceneRender
			int cosYaw = SceneGraph.cosYaw;    // MathUtils.cos[yaw] from setupSceneRender
			int sinPitch = SceneGraph.sinPitch;  // MathUtils.sin[pitch] from setupSceneRender
			int cosPitch = SceneGraph.cosPitch;  // MathUtils.cos[pitch] from setupSceneRender
			
			int local39 = local29 * sinPitch + vertexA * cosPitch >> 16;         // Same as line 4333
			int rotatedZ = local29 * cosPitch - vertexA * sinPitch >> 16;        // Same as line 4334
			int transformedY = relativeY * cosYaw - rotatedZ * sinYaw >> 16;     // Same as line 4335
			int depth = relativeY * sinYaw + rotatedZ * cosYaw >> 16;            // Same as line 4336
			
			if (depth >= 50) {
				screenX[validCorners] = Rasterizer.centerX + (local39 << 9) / depth;        // Same as line 4345
				screenY[validCorners] = Rasterizer.centerY + (transformedY << 9) / depth;   // Same as line 4346
				validCorners++;
			}
		}
		
		// Draw the four edges of the tile if all corners are visible
		if (validCorners == 4) {
			System.out.println("Drawing tile border at (" + tileX + ", " + tileZ + ") on plane " + plane);
			SoftwareRenderer.drawDiagonalLine(screenX[0], screenY[0], screenX[1], screenY[1], color); // SW to SE
			SoftwareRenderer.drawDiagonalLine(screenX[1], screenY[1], screenX[3], screenY[3], color); // SE to NE
			SoftwareRenderer.drawDiagonalLine(screenX[3], screenY[3], screenX[2], screenY[2], color); // NE to NW
			SoftwareRenderer.drawDiagonalLine(screenX[2], screenY[2], screenX[0], screenY[0], color); // NW to SW
		}
	}
	
	/**
	 * Checks if a point is inside a quadrilateral using cross product method
	 */
	private static boolean isPointInQuad(int px, int py, int[] quadX, int[] quadY) {
		// Use winding number method - check if point is inside by counting edge crossings
		// For a convex quadrilateral, we can use the cross product method
		
		// Check if point is on the same side of all 4 edges
		// SW -> SE -> NE -> NW -> SW (clockwise order)
		int[] orderedX = {quadX[0], quadX[1], quadX[3], quadX[2]}; // SW, SE, NE, NW
		int[] orderedY = {quadY[0], quadY[1], quadY[3], quadY[2]}; // SW, SE, NE, NW
		
		boolean positive = false;
		boolean negative = false;
		
		for (int i = 0; i < 4; i++) {
			int j = (i + 1) % 4;
			
			// Calculate cross product of edge vector and point vector
			int edgeX = orderedX[j] - orderedX[i];
			int edgeY = orderedY[j] - orderedY[i];
			int pointX = px - orderedX[i];
			int pointY = py - orderedY[i];
			
			long crossProduct = (long)edgeX * pointY - (long)edgeY * pointX;
			
			if (crossProduct > 0) positive = true;
			if (crossProduct < 0) negative = true;
			
			// If we have both positive and negative, point is outside
			if (positive && negative) return false;
		}
		
		return true; // All cross products have same sign, point is inside
	}
}

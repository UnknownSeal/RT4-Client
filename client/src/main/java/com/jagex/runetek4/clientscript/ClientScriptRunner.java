package com.jagex.runetek4.clientscript;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

import com.jagex.runetek4.*;
import com.jagex.runetek4.client.auth.CreateManager;
import com.jagex.runetek4.client.auth.LoginManager;
import com.jagex.runetek4.entity.Entity;
import com.jagex.runetek4.entity.PathingEntity;
import com.jagex.runetek4.entity.npc.Npc;
import com.jagex.runetek4.entity.npc.NpcList;
import com.jagex.runetek4.entity.player.Player;
import com.jagex.runetek4.entity.player.PlayerAppearance;
import com.jagex.runetek4.entity.player.PlayerList;
import com.jagex.runetek4.entity.player.PlayerSkillXpTable;
import com.jagex.runetek4.graphics.animation.ProjAnimNode;
import com.jagex.runetek4.graphics.animation.ProjectileAnimation;
import com.jagex.runetek4.graphics.animation.SpotAnim;
import com.jagex.runetek4.graphics.animation.SpotAnimEntity;
import com.jagex.runetek4.audio.core.SoundPlayer;
import com.jagex.runetek4.audio.midi.MidiPlayer;
import com.jagex.runetek4.audio.streaming.MusicPlayer;
import com.jagex.runetek4.data.cache.cs.ClientScript;
import com.jagex.runetek4.scene.Camera;
import com.jagex.runetek4.ui.chat.Chat;
import com.jagex.runetek4.ui.chat.ClanChat;
import com.jagex.runetek4.ui.chat.OverHeadChat;
import com.jagex.runetek4.ui.chat.QuickChatPhrase;
import com.jagex.runetek4.client.*;
import com.jagex.runetek4.config.types.npc.NpcType;
import com.jagex.runetek4.config.types.obj.ObjType;
import com.jagex.runetek4.data.cache.media.Font;
import com.jagex.runetek4.ui.component.Component;
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

import static com.jagex.runetek4.clientscript.ClientScriptOpcode.*;
import static com.jagex.runetek4.clientscript.ClientScriptOpcode.CLIENTSCRIPT_117;
import static com.jagex.runetek4.clientscript.ClientScriptOpcode.IF_ON_COMPONENT;
import static com.jagex.runetek4.clientscript.ClientScriptOpcode.IF_ON_COMPONENT2;
import static com.jagex.runetek4.clientscript.ClientScriptOpcode.IF_ON_COMPONENT3;
import static com.jagex.runetek4.clientscript.ClientScriptOpcode.IF_ON_COMPONENT4;
import static com.jagex.runetek4.network.ClientProt.*;

public final class ClientScriptRunner {

	@OriginalMember(owner = "client!bm", name = "p", descriptor = "Lclient!na;")
	public static final JString TYPE_SIG_U1 = JString.parse("(U1");

	@OriginalMember(owner = "runetek4.client!wh", name = "u", descriptor = "Lclient!na;")
	public static final JString TYPE_SIG_U2 = JString.parse("(U2");

	@OriginalMember(owner = "runetek4.client!mj", name = "g", descriptor = "Lclient!na;")
	public static final JString TYPE_SIG_U3 = JString.parse("(U3");

	@OriginalMember(owner = "runetek4.client!hh", name = "a", descriptor = "Lclient!na;")
	public static final JString TYPE_SIG_U4 = JString.parse("(U4");

	@OriginalMember(owner = "runetek4.client!tb", name = "P", descriptor = "Lclient!na;")
	public static final JString TYPE_SIG_U5 = JString.parse("(U5");

	@OriginalMember(owner = "runetek4.client!jh", name = "g", descriptor = "Lclient!na;")
	public static final JString TYPE_SIG_DNS = JString.parse("(Udns");

	@OriginalMember(owner = "runetek4.client!lh", name = "z", descriptor = "Lclient!na;")
	public static final JString TYPE_SIG_UNION_X = JString.parse("(U (X");

	@OriginalMember(owner = "client!bd", name = "b", descriptor = "Lclient!na;")
	public static final JString TYPE_SIG_UNION_Y = JString.parse("(U(Y");

	@OriginalMember(owner = "runetek4.client!oj", name = "p", descriptor = "I")
	public static final int COLOR_LIGHT_BROWN = 2301979;

	@OriginalMember(owner = "runetek4.client!ec", name = "l", descriptor = "I")
	public static final int COLOR_ORANGE_BROWN = 5063219;

	@OriginalMember(owner = "runetek4.client!rl", name = "Z", descriptor = "I")
	public static final int COLOR_LIGHT_GRAY = 7759444;

	@OriginalMember(owner = "client!bj", name = "V", descriptor = "I")
	public static final int COLOR_DARK_RED = 3353893;

	@OriginalMember(owner = "runetek4.client!pg", name = "V", descriptor = "I")
	public static final int MAX_CALL_STACK_DEPTH = 50;

	@OriginalMember(owner = "runetek4.client!ab", name = "j", descriptor = "[Lclient!na;")
	public static final JString[] scriptStringValues = new JString[1000];

	@OriginalMember(owner = "runetek4.client!uj", name = "t", descriptor = "[I")
	public static final int[] scriptIntValues = new int[1000];

	@OriginalMember(owner = "client!fl", name = "Q", descriptor = "Lclient!na;")
	public static final JString EMPTY_STRING = JString.parse("");

	@OriginalMember(owner = "client!bb", name = "A", descriptor = "Lclient!na;")
	public static final JString SCOPE_SEPARATOR = JString.parse("::");

	@OriginalMember(owner = "client!be", name = "ib", descriptor = "Lclient!na;")
	public static final JString EVENT_OPBASE = JString.parse("event_opbase");

	@OriginalMember(owner = "client!da", name = "O", descriptor = "Lclient!na;")
	public static final JString ERROR_VIA = JString.parse("(U0a )2 via: ");

	@OriginalMember(owner = "client!fl", name = "H", descriptor = "Lclient!na;")
	public static final JString SPRITE_TAG_0 = JString.parse("<img=0>");

	@OriginalMember(owner = "runetek4.client!nd", name = "b", descriptor = "Lclient!na;")
	public static final JString ERROR_PREFIX = JString.parse("Clientscript error in: ");

	@OriginalMember(owner = "runetek4.client!hm", name = "R", descriptor = "Lclient!na;")
	public static final JString SPRITE_TAG_1 = JString.parse("<img=1>");

	@OriginalMember(owner = "runetek4.client!hn", name = "K", descriptor = "Ljava/util/Calendar;")
	public static final Calendar calendar = Calendar.getInstance();

	@OriginalMember(owner = "runetek4.client!kk", name = "m", descriptor = "Lclient!na;")
	public static final JString CS_ERROR = JString.parse("Clientscript error )2 check log for details");

	@OriginalMember(owner = "client!fe", name = "nc", descriptor = "[Lclient!hj;")
	public static final GoSubFrame[] callStack = new GoSubFrame[50];

	@OriginalMember(owner = "client!ee", name = "j", descriptor = "[I")
	public static final int[] stackDepthCounters = new int[5];

	@OriginalMember(owner = "runetek4.client!oe", name = "i", descriptor = "[[I")
	public static final int[][] nestedStackValues = new int[5][5000];

	@OriginalMember(owner = "runetek4.client!rl", name = "eb", descriptor = "Lclient!na;")
	public static final JString ERROR_IN_SCRIPT = JString.parse("(U0a )2 in: ");

	@OriginalMember(owner = "client!fe", name = "I", descriptor = "Lclient!na;")
	public static final JString SPACE_STRING = JString.parse(" ");

	@OriginalMember(owner = "client!dc", name = "M", descriptor = "Lclient!na;")
	public static final JString URL_CONFIG_SUFFIX = JString.parse(")4");

	@OriginalMember(owner = "runetek4.client!he", name = "gb", descriptor = "Lclient!na;")
	public static final JString URL_DOMAIN_WWW = JString.parse("www");

	@OriginalMember(owner = "client!e", name = "Tc", descriptor = "Lclient!na;")
	public static final JString URL_DOMAIN_WTQA = JString.parse("www)2wtqa");

	@OriginalMember(owner = "runetek4.client!lk", name = "J", descriptor = "Lclient!na;")
	public static final JString URL_SETTINGS_PARAM = JString.parse(")4p=");

	@OriginalMember(owner = "client!en", name = "x", descriptor = "Lclient!na;")
	public static final JString URL_PROTOCOL_HTTP = JString.parse("http:)4)4");

	@OriginalMember(owner = "client!gf", name = "I", descriptor = "Lclient!na;")
	public static final JString URL_RUNESCAPE_LANG_PARAM = JString.parse(")3runescape)3com)4l=");

	@OriginalMember(owner = "runetek4.client!v", name = "a", descriptor = "Lclient!na;")
	public static final JString URL_AFFILIATE_PARAM = JString.parse(")4a=");

	@OriginalMember(owner = "client!ch", name = "C", descriptor = "[[I")
	public static final int[][] entityCountsPerTile = new int[104][104];

	@OriginalMember(owner = "runetek4.client!n", name = "e", descriptor = "Lclient!na;")
	public static final JString ESCAPE_CHAR = JString.parse(")2");

	@OriginalMember(owner = "runetek4.client!ob", name = "p", descriptor = "Lclient!na;")
	public static final JString ERROR_MISSING_GOSUB = JString.parse("(U0a )2 non)2existant gosub script)2num: ");

	@OriginalMember(owner = "runetek4.client!af", name = "m", descriptor = "Lclient!na;")
	public static final JString HTML_LINE_BREAK = JString.parse("<br>");

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
	public static Component[] tempComponentArray;

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
	public static short minZoomScale = 256;

	@OriginalMember(owner = "client!an", name = "db", descriptor = "S")
	public static short maxZoomScale = 205;

	@OriginalMember(owner = "runetek4.client!mc", name = "tb", descriptor = "S")
	public static short minZoomConstraint = 1;

	@OriginalMember(owner = "runetek4.client!ac", name = "k", descriptor = "S")
	public static short maxZoomConstraint = 32767;

	@OriginalMember(owner = "runetek4.client!nc", name = "n", descriptor = "I")
	public static int calculatedViewportWidth = 0;

	@OriginalMember(owner = "runetek4.client!tm", name = "i", descriptor = "I")
	public static int calculatedViewportHeight = 0;

	@OriginalMember(owner = "client!bn", name = "eb", descriptor = "I")
	public static int anInt773 = 0;

	@OriginalMember(owner = "client!ah", name = "n", descriptor = "I")
	public static int anInt983 = 0;

	@OriginalMember(owner = "runetek4.client!sc", name = "p", descriptor = "I")
	public static int anInt5029 = 0;

	@OriginalMember(owner = "runetek4.client!kd", name = "yb", descriptor = "S")
	public static short maxAspectRatio = 32767;

	@OriginalMember(owner = "client!ee", name = "f", descriptor = "S")
	public static short minAspectRatio = 1;

	@OriginalMember(owner = "runetek4.client!kk", name = "j", descriptor = "I")
	public static int anInt3325 = 0;

	@OriginalMember(owner = "runetek4.client!vk", name = "f", descriptor = "[[[B")
	public static byte[][][] tileMarkings;

	@OriginalMember(owner = "runetek4.client!vg", name = "b", descriptor = "S")
	public static short minDistanceScale = 256;

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
	public static short maxDistanceScale = 320;

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
						Protocol.outboundBuffer.pIsaac1(ClientProt.OPHELDT);
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
				if (opcode == PUSH_CONSTANT_INT) {
					return accumulator;
				}
				if (opcode == OP_SUBTRACT) {
					nextOperator = 1;
				}
				if (opcode == OP_DIVIDE) {
					nextOperator = 2;
				}
				if (opcode == PUSH_VARP) {
                    // load_skill_level {skill}
					value = PlayerSkillXpTable.boostedLevels[script[pc++]]; // Current skill level (with boosts)
                }
				if (opcode == OP_MULTIPLY) {
					nextOperator = 3;
				}
				if (opcode == 2) {
                    // load_skill_base_level {skill}
					value = PlayerSkillXpTable.baseLevels[script[pc++]]; // Base skill level (without boosts)
				}
				if (opcode == 3) {
                    // load_skill_exp {skill}
					value = PlayerSkillXpTable.experience[script[pc++]]; // Total XP in skill
				}
				@Pc(124) int interfaceId;
				@Pc(135) Component com;
				@Pc(140) int objTypeId;
				@Pc(152) int slotIndex;
				if (opcode == LOAD_INV_COUNT) { // load_inv_count {interface id} {obj id}
					interfaceId = script[pc++] << 16; // Interface ID
					@Pc(131) int componentId = interfaceId + script[pc++]; // Component ID
					com = ComponentList.getComponent(componentId);
					objTypeId = script[pc++];
					if (objTypeId != -1 && (!ObjTypeList.get(objTypeId).members || LoginManager.membersWorld)) {
						for (slotIndex = 0; slotIndex < com.invSlotObjId.length; slotIndex++) {
							if (objTypeId + 1 == com.invSlotObjId[slotIndex]) { // +1 because inventory uses 1-based IDs
								value += com.invSlotObjCount[slotIndex]; // Accumulate count
							}
						}
					}
				}
				if (opcode == LOAD_VAR) {
                    // load_var {id}
					value = VarpDomain.activeVarps[script[pc++]];
				}
				if (opcode == LOAD_NEXT_LEVEL_XP) {
                    // load_next_level_xp {skill}
					value = PlayerSkillXpTable.xpLevelLookup[PlayerSkillXpTable.baseLevels[script[pc++]] - 1];
				}
				if (opcode == LOAD_VARP_PERCENT) {
                    // Load varp as percentage (0-100)
                    // 46875 is the max varp value that maps to 100%
					value = VarpDomain.activeVarps[script[pc++]] * 100 / 46875;
				}
				if (opcode == LOAD_COMBAT_LEVEL) {
                    // load_combat_level
					value = PlayerList.self.combatLevel;
				}
				if (opcode == LOAD_TOTAL_LEVEL) {
                    // load_total_level
					for (interfaceId = 0; interfaceId < 25; interfaceId++) {
						if (PlayerSkillXpTable.ENABLED_SKILLS[interfaceId]) {
							value += PlayerSkillXpTable.baseLevels[interfaceId];
						}
					}
				}
				if (opcode == LOAD_INV_CONTAINS) {
                    // load_inv_contains {interface id} {obj id}
                    // Check if inventory contains at least one of an item
					interfaceId = script[pc++] << 16;
					interfaceId += script[pc++];
					com = ComponentList.getComponent(interfaceId);
					objTypeId = script[pc++];
					if (objTypeId != -1 && (!ObjTypeList.get(objTypeId).members || LoginManager.membersWorld)) {
						for (slotIndex = 0; slotIndex < com.invSlotObjId.length; slotIndex++) {
							if (com.invSlotObjId[slotIndex] == objTypeId + 1) {
								value = 999999999; // "found" value
								break;
							}
						}
					}
				}
				if (opcode == LOAD_ENERGY) {
                    // load_energy
					value = Player.runEnergy;
				}
				if (opcode == LOAD_WEIGHT) {
                    // load_weight
					value = Player.weightCarried;
				}
				if (opcode == LOAD_BOOL) {
                    // load_bool {varp} {bit: 0..31}
                    // Extract a single bit from a varp as boolean (0 or 1)
					interfaceId = VarpDomain.activeVarps[script[pc++]]; // The varp value
					@Pc(353) int leastSignificantBit = script[pc++];
					value = (0x1 << leastSignificantBit & interfaceId) == 0 ? 0 : 1; // Test if bit is set
				}
				if (opcode == LOAD_VARBIT) {
					interfaceId = script[pc++];
					value = VarpDomain.getVarbitValue(interfaceId);
				}
				if (opcode == COORD_X) {
					value = (PlayerList.self.xFine >> 7) + Camera.sceneBaseTileX;
				}
				if (opcode == COORD_Z) {
					value = (PlayerList.self.zFine >> 7) + Camera.sceneBaseTileZ;
				}
				if (opcode == PUSH_CONSTANT) {
					value = script[pc++];
				}

                // Accumulator operations, combine value with accumulator
				if (nextOperator == 0) {
                    // Apply pending operation
					if (accumulatorMode == 0) {
						accumulator += value; // Add
					}
					if (accumulatorMode == 1) {
						accumulator -= value; // Subtract
                    }
					if (accumulatorMode == 2 && value != 0) {
						accumulator /= value; // Divide
					}
					if (accumulatorMode == 3) {
						accumulator *= value; // Multiply
					}
					accumulatorMode = 0; // Reset operator mode
				} else {
                    // Set operator for next iteration (opcodes 15-17 set nextOperator)
					accumulatorMode = nextOperator;
				}
			}
		} catch (@Pc(464) Exception exception) {
			return -1;
		}
	}

	@OriginalMember(owner = "runetek4.client!md", name = "a", descriptor = "(Lclient!be;I)Z")
    /**
     * Evaluates CS1 comparison conditions for component visibility.
     * Components have multiple CS1 scripts that must ALL evaluate to true.
     * Used for things like "show if skill >= 50" or "hide if item count < 1"
     */

    public static boolean isTrue(@OriginalArg(0) Component component) {
		if (component.cs1ComparisonOpcodes == null) {
			return false; // No conditions = hidden
        }
        // Check all conditions ALL must pass (AND logic)
		for (@Pc(14) int i = 0; i < component.cs1ComparisonOpcodes.length; i++) {
			@Pc(34) int value = executeClientscript(i, component); // Run CS1 script to get value
			@Pc(39) int operand = component.scriptOperand[i]; // Comparison target

            // 1=equal, 2=less_than, 3=greater_than, 4=not_equal
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
		return true; // All conditions passed
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
				@Pc(803) int tempInt1;
				@Pc(652) int i;
				@Pc(809) int tempInt2;
				@Pc(609) JString tempString1;
				if (opcode < 100) {
					// core language ops (not commands)

					if (opcode == PUSH_CONSTANT_INT) {
						// push_constant_int
						scriptIntValues[isp++] = intOperands[pc];
						continue;
					}
					if (opcode == PUSH_VARP) {
						// push_varp
						id = intOperands[pc];
						scriptIntValues[isp++] = VarpDomain.activeVarps[id];
						continue;
					}
					if (opcode == POP_VARP) {
						// pop_varp
						id = intOperands[pc];
						isp--; // Decrement then read
						VarpDomain.setVarpClient(id, scriptIntValues[isp]);
						continue;
					}
					if (opcode == PUSH_CONSTANT_STRING) {
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
					if (opcode == RETURN) {
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
					if (opcode == PUSH_VARBIT) {
						// push_varbit
						id = intOperands[pc];
						scriptIntValues[isp++] = VarpDomain.getVarbitValue(id);
						continue;
					}
					if (opcode == POP_VARBIT) {
						// pop_varbit
						id = intOperands[pc];
						isp--; // Decrement then read
						VarpDomain.setVarbitClient(id, scriptIntValues[isp]);
						continue;
					}
					if (opcode == BRANCH_LESS_THAN_OR_EQUALS) {
						// branch_less_than_or_equals
						isp -= 2;
						if (scriptIntValues[isp + 1] >= scriptIntValues[isp]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (opcode == BRANCH_GREATER_THAN_OR_EQUALS) {
						// branch_greater_than_or_equals
						isp -= 2;
						if (scriptIntValues[isp] >= scriptIntValues[isp + 1]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (opcode == PUSH_INT_LOCAL) {
						// push_int_local
						scriptIntValues[isp++] = intLocals[intOperands[pc]];
						continue;
					}
					@Pc(555) int localIndex;
					if (opcode == POP_INT_LOCAL) {
						// pop_int_local
						localIndex = intOperands[pc];
						isp--; // Decrement then read
						intLocals[localIndex] = scriptIntValues[isp];
						continue;
					}
					if (opcode == PUSH_STRING_LOCAL) {
						// push_string_local
						scriptStringValues[ssp++] = stringLocals[intOperands[pc]];
						continue;
					}
					if (opcode == POP_STRING_LOCAL) {
						// pop_string_local
						localIndex = intOperands[pc];
						ssp--;
						stringLocals[localIndex] = scriptStringValues[ssp];
						continue;
					}
					if (opcode == JOIN_STRING) {
						// join_string
						id = intOperands[pc];
						ssp -= id;
                        tempString1 = JString.join(ssp, id, scriptStringValues);
						scriptStringValues[ssp++] = tempString1;
						continue;
					}
					if (opcode == POP_INT_DISCARD) {
						// pop_int_discard
						isp--; // Decrement then read
						continue;
					}
					if (opcode == POP_STRING_DISCARD) {
						// pop_string_discard
						ssp--;
						continue;
					}
					if (opcode == GOSUB_WITH_PARAMS) {
						// gosub_with_params, call another ClientScript
						id = intOperands[pc]; // Script ID to call
						@Pc(642) ClientScript invokeScript = ClientScriptList.get(id);

                        // Allocate local variable storage for the called script
						@Pc(646) int[] invokeScriptIntLocals = new int[invokeScript.localIntCount];
						@Pc(650) JString[] invokeScriptStringLocals = new JString[invokeScript.localStringCount];

                        // Copy arguments from stack to called scripts locals
                        // Int arguments from the int stack
                        for (i = 0; i < invokeScript.intArgs; i++) {
							invokeScriptIntLocals[i] = scriptIntValues[i + isp - invokeScript.intArgs];
						}

                        // String arguments from the string stack
                        for (i = 0; i < invokeScript.stringArgs; i++) {
							invokeScriptStringLocals[i] = scriptStringValues[i + ssp - invokeScript.stringArgs];
						}
						isp -= invokeScript.intArgs;
						ssp -= invokeScript.stringArgs;

                        // Save current execution state on the call stack
						@Pc(705) GoSubFrame frame = new GoSubFrame();
						frame.stringLocals = stringLocals; // Save current string locals
						frame.localInts = intLocals; // Save current int locals
						frame.pc = pc; // Save program counter
						frame.script = clientScript; // Save current script
						if (fp >= callStack.length) {
							throw new RuntimeException(); // Call stack overflow (max 50 deep)
						}

                        // Switch to the called script
						clientScript = invokeScript;
						pc = -1;
						callStack[fp++] = frame; // Push frame onto call stack
						intLocals = invokeScriptIntLocals; // Switch to new locals
						intOperands = invokeScript.intOperands; // Switch to new operands
						opcodes = invokeScript.opcodes; // Switch to new opcodes
						stringLocals = invokeScriptStringLocals; // Switch to new string locals
						continue;
					}
					if (opcode == PUSH_VARC_INT) {
						// push_varc_int
						scriptIntValues[isp++] = VarcDomain.varcs[intOperands[pc]];
						continue;
					}
					if (opcode == POP_VARC_INT) {
						// pop_varc_int
						id = intOperands[pc];
						isp--; // Decrement then read
						VarcDomain.varcs[id] = scriptIntValues[isp];
						ClientServerStateSync.setVarcClient(id);
						continue;
					}
					if (opcode == DEFINE_ARRAY) {
                        // define_array
                        // Arrays are global and indexed by ID, used for things like NPC lists, item lists etc
						id = intOperands[pc] >> 16; // Array ID
						isp--; // Decrement then read
						tempInt1 = scriptIntValues[isp]; // New array size
						tempInt2 = intOperands[pc] & 0xFFFF; // Array type/opcode
						if (tempInt1 >= 0 && tempInt1 <= 5000) { // Validate size
							stackDepthCounters[id] = tempInt1; // Store the array size

                            // Determine default value based on array type
							@Pc(828) byte defaultValue = -1; // Most arrays default to -1
							if (tempInt2 == 105) { // Store the array size
								defaultValue = 0;
							}

                            // Initialize all elements to the default value
							i = 0;
							while (true) {
								if (tempInt1 <= i) {
									continue nextOp;
								}
								nestedStackValues[id][i] = defaultValue;
								i++;
							}
						}
						throw new RuntimeException(); // Size out of valid range
					}
					if (opcode == ARRAY_GET) {
                        // array_get
						id = intOperands[pc]; // Array ID
						isp--; // Decrement then read
						tempInt2 = scriptIntValues[isp]; // Array index
						if (tempInt2 >= 0 && tempInt2 < stackDepthCounters[id]) { // Bounds check
							scriptIntValues[isp++] = nestedStackValues[id][tempInt2]; // Push value
							continue;
						}
						throw new RuntimeException(); // Index out of bounds
					}
					if (opcode == ARRAY_SET) {
                        // array_set
						id = intOperands[pc]; // Array ID
						isp -= 2;
						tempInt2 = scriptIntValues[isp]; // Array index
						if (tempInt2 >= 0 && tempInt2 < stackDepthCounters[id]) { // Bounds check
							nestedStackValues[id][tempInt2] = scriptIntValues[isp + 1]; // Set value
							continue;
						}
						throw new RuntimeException(); // Index out of bounds
					}
					if (opcode == PUSH_VARC_STRING) {
						value = VarcDomain.varcstrs[intOperands[pc]];
						if (value == null) {
							value = VarpDomain.NULL;
						}
						scriptStringValues[ssp++] = value;
						continue;
					}
					if (opcode == POP_VARC_STRING) {
						id = intOperands[pc];
						ssp--;
						VarcDomain.varcstrs[id] = scriptStringValues[ssp];
						ClientServerStateSync.setVarcstrClient(id);
						continue;
					}
					if (opcode == SWITCH) {
						@Pc(992) HashTable table = clientScript.switchTables[intOperands[pc]];
						isp--; // Decrement then read
						@Pc(1002) IntWrapper node = (IntWrapper) table.get((long) scriptIntValues[isp]);
						if (node != null) {
							pc += node.value;
						}
						continue;
					}
				}

                // Many component opcodes have a variant: cc_ (active) and .cc_ (secondary active)
                // This flag determines which active component to use
                @Pc(1020) boolean secondary;
				if (intOperands[pc] == 1) {
					secondary = true; // false = cc_ (primary), true = .cc_ (secondary)
				} else {
					secondary = false; // Use primaryActiveComponent (cc_ prefix)
				}
				@Pc(1182) Component createdComponent;
				@Pc(1052) int j;
				@Pc(1063) Component component;
				@Pc(1087) int childId;
				@Pc(1256) Component foundComponent;
				if (opcode < 300) {
					if (opcode == CC_CREATE) {
						// cc_create
						isp -= 3;
						tempInt2 = scriptIntValues[isp];
						tempInt1 = scriptIntValues[isp + 1];
						j = scriptIntValues[isp + 2];
						if (tempInt1 != 0) {
							component = ComponentList.getComponent(tempInt2);
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
							local1137.type = tempInt1;
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
					@Pc(1204) Component parentComponent;
					if (opcode == CC_DELETE) {
						// cc_delete
						createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
						if (createdComponent.createdComponentId == -1) {
							if (!secondary) {
								throw new RuntimeException("Tried to cc_delete static active-component!");
							}
							throw new RuntimeException("Tried to .cc_delete static .active-component!");
						}
						parentComponent = ComponentList.getComponent(createdComponent.id);
						parentComponent.createdComponents[createdComponent.createdComponentId] = null;
						ComponentList.redraw(parentComponent);
						continue;
					}
					if (opcode == CC_DELETEALL) {
						// cc_deleteall
						isp--; // Decrement then read
						createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
						createdComponent.createdComponents = null;
						ComponentList.redraw(createdComponent);
						continue;
					}
					if (opcode == CC_FIND) {
						isp -= 2;
						tempInt2 = scriptIntValues[isp];
						tempInt1 = scriptIntValues[isp + 1];
						foundComponent = ComponentList.getCreatedComponent(tempInt2, tempInt1);
						if (foundComponent != null && tempInt1 != -1) {
							scriptIntValues[isp++] = 1;
							if (secondary) {
								secondaryActiveComponent = foundComponent;
							} else {
								primaryActiveComponent = foundComponent;
							}
							continue;
						}
						scriptIntValues[isp++] = 0;
						continue;
					}
					if (opcode == IF_FIND) {
						isp--; // Decrement then read
						tempInt2 = scriptIntValues[isp];
						parentComponent = ComponentList.getComponent(tempInt2);
						if (parentComponent == null) {
							scriptIntValues[isp++] = 0;
						} else {
							scriptIntValues[isp++] = 1;
							if (secondary) {
								secondaryActiveComponent = parentComponent;
							} else {
								primaryActiveComponent = parentComponent;
							}
						}
						continue;
					}
				} else {
					@Pc(12388) boolean isFemale;
					if (opcode < 500) {
						if (opcode == SETIDKIT) {
							isp -= 2;
							tempInt1 = scriptIntValues[isp + 1];
							tempInt2 = scriptIntValues[isp];
							for (j = 0; j < PlayerAppearance.MALE_FEATURES.length; j++) {
								if (tempInt2 == PlayerAppearance.MALE_FEATURES[j]) {
									PlayerList.self.appearance.setIdentikit(j, tempInt1);
									continue nextOp;
								}
							}
							j = 0;
							while (true) {
								if (j >= PlayerAppearance.FEMALE_FEATURES.length) {
									continue nextOp;
								}
								if (tempInt2 == PlayerAppearance.FEMALE_FEATURES[j]) {
									PlayerList.self.appearance.setIdentikit(j, tempInt1);
									continue nextOp;
								}
								j++;
							}
						}
						if (opcode == SETCOLOR) {
							isp -= 2;
							tempInt2 = scriptIntValues[isp];
							tempInt1 = scriptIntValues[isp + 1];
							PlayerList.self.appearance.setColor(tempInt2, tempInt1);
							continue;
						}
						if (opcode == SETGENDER) {
							isp--; // Decrement then read
							isFemale = scriptIntValues[isp] != 0;
							PlayerList.self.appearance.setGender(isFemale);
							continue;
						}
					} else {
						@Pc(1552) boolean local1552;

                        // 1100-1199: cc_ commands, operate on active component
                        // 2100-2199: if_ commands, operate on component from stack
                        if ((opcode < 1000 || opcode >= 1100) && (opcode < 2000 || opcode >= 2100)) {
							@Pc(2522) JString chatTypedLowercase;
							if (opcode >= 1100 && opcode < 1200 || !(opcode < 2100 || opcode >= 2200)) {
								if (opcode < 2000) {
                                    // cc_ commands, use the currently active component (set by cc_find/cc_create)
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
								} else {
                                    // if_ commands,  pop component ID from stack
									opcode -= 1000; // Adjust opcode to match cc_ version
									isp--; // Decrement then read
									createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
								}
								if (opcode == CC_SETSCROLLPOS) {
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
										ClientServerStateSync.setComponentScrollPositionClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == CC_SETCOLOR) {
									// setcolor
									isp--; // Decrement then read
									createdComponent.color = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									if (createdComponent.createdComponentId == -1) {
										ClientServerStateSync.setComponentColorClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == CC_SETFILL) {
									// setfill
									isp--; // Decrement then read
									createdComponent.filled = scriptIntValues[isp] == 1;
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == CC_SETTRANS) {
									// settrans
									isp--; // Decrement then read
									createdComponent.alpha = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == CC_SETLINEWID) {
									// setlinewid
									isp--; // Decrement then read
									createdComponent.lineWidth = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == CC_SETGRAPHIC) {
									// setgraphic
									isp--; // Decrement then read
									createdComponent.spriteId = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == CC_SETANGLE) {
									isp--; // Decrement then read
									createdComponent.angle2d = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == CC_SETTILING) {
									// settiling
									isp--; // Decrement then read
									createdComponent.spriteTiling = scriptIntValues[isp] == 1;
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == CC_SETMODEL) {
									// setmodel
									createdComponent.modelType = 1;
									isp--; // Decrement then read
									createdComponent.modelId = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									if (createdComponent.createdComponentId == -1) {
										ClientServerStateSync.setComponentModelClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == CC_SETMODELANGLE) {
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
										ClientServerStateSync.setComponentModelAngleClient(createdComponent.id);
										ClientServerStateSync.setComponentModelOffsetClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == CC_SETMODELANIM) {
									// setmodelanim
									isp--; // Decrement then read
									tempInt1 = scriptIntValues[isp];
									if (createdComponent.modelSeqId != tempInt1) {
										createdComponent.modelSeqId = tempInt1;
										createdComponent.animationId = 0;
										createdComponent.animationDelay = 0;
										createdComponent.animationFrame = 1;
										ComponentList.redraw(createdComponent);
									}
									if (createdComponent.createdComponentId == -1) {
										ClientServerStateSync.setComponentModelAnimClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == CC_SETMODELORTHOG) {
									// setmodelorthog
									isp--; // Decrement then read
									createdComponent.modelOrtho = scriptIntValues[isp] == 1;
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == CC_SETTEXT) {
									// settext
									ssp--;
									chatTypedLowercase = scriptStringValues[ssp];
									if (!chatTypedLowercase.strEquals(createdComponent.text)) {
										createdComponent.text = chatTypedLowercase;
										ComponentList.redraw(createdComponent);
									}
									if (createdComponent.createdComponentId == -1) {
										ClientServerStateSync.setComponentTextClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == CC_SETTEXTFONT) {
									// settextfont
									isp--; // Decrement then read
									createdComponent.fontId = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == CC_SETTEXTALIGN) {
									// settextalign
									isp -= 3;
									createdComponent.halign = scriptIntValues[isp];
									createdComponent.valign = scriptIntValues[isp + 1];
									createdComponent.vpadding = scriptIntValues[isp + 2];
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == CC_SETTEXTSHADOW) {
									// settextshadow
									isp--; // Decrement then read
									createdComponent.shadowed = scriptIntValues[isp] == 1;
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == CC_SETOUTLINE) {
									isp--; // Decrement then read
									createdComponent.outlineThickness = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == CC_SETSHADOWCOLOR) {
									isp--; // Decrement then read
									createdComponent.shadowColor = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == CC_SETVFLIP) {
									isp--; // Decrement then read
									createdComponent.vFlip = scriptIntValues[isp] == 1;
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == CC_SETHFLIP) {
									isp--; // Decrement then read
									createdComponent.hFlip = scriptIntValues[isp] == 1;
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == CC_SETSCROLLSIZE) {
									isp -= 2;
									createdComponent.scrollMaxH = scriptIntValues[isp];
									createdComponent.scrollMaxV = scriptIntValues[isp + 1];
									ComponentList.redraw(createdComponent);
									if (createdComponent.type == 0) {
										ComponentList.updateContainerLayout(createdComponent, false);
									}
									continue;
								}
								if (opcode == CC_SETCOMPONENTSHORTS) {
									isp -= 2;
									createdComponent.aShort11 = (short) scriptIntValues[isp];
									createdComponent.aShort10 = (short) scriptIntValues[isp + 1];
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == CC_SETHASALPHA) {
									isp--; // Decrement then read
									createdComponent.hasAlpha = scriptIntValues[isp] == 1;
									ComponentList.redraw(createdComponent);
									continue;
								}
								if (opcode == CC_SETZOOM) {
									isp--; // Decrement then read
									createdComponent.modelZoom = scriptIntValues[isp];
									ComponentList.redraw(createdComponent);
									if (createdComponent.createdComponentId == -1) {
										ClientServerStateSync.setComponentModelAngleClient(createdComponent.id);
									}
									continue;
								}
							} else if (opcode >= 1200 && opcode < 1300 || !(opcode < 2200 || opcode >= 2300)) {
								if (opcode < 2000) {
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
								} else {
									isp--; // Decrement then read
									createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
									opcode -= 1000;
								}
								ComponentList.redraw(createdComponent);
								if (opcode == CC_SETOBJECT || opcode == CC_SETOBJECT_NONUM) {
                                    // setobject / setobject_nonum - Display item model on component
                                    // 1200 shows stack count, 1205 hides it
									isp -= 2;
									j = scriptIntValues[isp + 1]; // Item count
									tempInt1 = scriptIntValues[isp]; // Object type ID
									if (createdComponent.createdComponentId == -1) {
                                        // For static components, notify server of changes
										ClientServerStateSync.setComponentObjClient(createdComponent.id);
										ClientServerStateSync.setComponentModelAngleClient(createdComponent.id);
										ClientServerStateSync.setComponentModelOffsetClient(createdComponent.id);
									}
									if (tempInt1 == -1) {
                                        // Clear the object
										createdComponent.modelId = -1;
										createdComponent.modelType = 1;
										createdComponent.objId = -1;
									} else {
                                        // Set object and copy its 2D display properties
										createdComponent.objId = tempInt1;
										createdComponent.objCount = j;
										@Pc(13416) ObjType objType = ObjTypeList.get(tempInt1);

                                        // Copy model positioning from object config
										createdComponent.modelYOffset = objType.zAngle2D; // Z angle
										createdComponent.modelXOffset = objType.xof2d; // X offset
										createdComponent.modelXAngle = objType.xan2d; // X angle
										createdComponent.modelZOffset = objType.yof2d; // Y offset
										createdComponent.modelYAngle = objType.yan2d; // Y angle
										createdComponent.modelZoom = objType.zoom2d; // Zoom level

                                        // Scale zoom to fit component size
										if (createdComponent.maxModelSize > 0) {
											createdComponent.modelZoom = createdComponent.modelZoom * 32 / createdComponent.maxModelSize;
										} else if (createdComponent.baseWidth > 0) {
											createdComponent.modelZoom = createdComponent.modelZoom * 32 / createdComponent.baseWidth;
										}

										if (opcode == 1205) {
											createdComponent.objDrawText = false; // Hide count text
										} else {
											createdComponent.objDrawText = true; // Show count
										}
									}
									continue;
								}
								if (opcode == CC_SETNPCHEAD) {
									// setnpchead
                                    // Display NPC chathead model
									createdComponent.modelType = 2; // Model type 2 = NPC head
									isp--; // Decrement then read
									createdComponent.modelId = scriptIntValues[isp];  // NPC type ID
									if (createdComponent.createdComponentId == -1) {
										ClientServerStateSync.setComponentModelClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == CC_SETPLAYERHEAD_SELF) {
									// setplayerhead_self
                                    // Display players chathead
									createdComponent.modelType = 3; // Model type 3 = player head
									createdComponent.modelId = PlayerList.self.appearance.getHeadModelId();
									if (createdComponent.createdComponentId == -1) {
										ClientServerStateSync.setComponentModelClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == CC_SETNPCMODEL) {
									// setnpcmodel
                                    // Display full NPC model
									createdComponent.modelType = 6; // Model type 6 = NPC body
									isp--; // Decrement then read
									createdComponent.modelId = scriptIntValues[isp];
									if (createdComponent.createdComponentId == -1) {
										ClientServerStateSync.setComponentModelClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == CC_SETPLAYERMODEL) {
									createdComponent.modelType = 5;
									isp--; // Decrement then read
									createdComponent.modelId = scriptIntValues[isp];
									if (createdComponent.createdComponentId == -1) {
										ClientServerStateSync.setComponentModelClient(createdComponent.id);
									}
									continue;
								}
							} else if (opcode >= 1300 && opcode < 1400 || opcode >= 2300 && opcode < 2400) {
								if (opcode >= 2000) {
									// if_
									isp--; // Decrement then read
									createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
									opcode -= 1000;
								} else {
									// cc_
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
								}
								if (opcode == CC_SETOP) {
									isp--; // Decrement then read
									tempInt1 = scriptIntValues[isp] - 1;
									if (tempInt1 >= 0 && tempInt1 <= 9) {
										ssp--;
										createdComponent.method480(scriptStringValues[ssp], tempInt1);
										continue;
									}
									ssp--;
									continue;
								}
								if (opcode == CC_SETPARENT) {
									isp -= 2;
									j = scriptIntValues[isp + 1];
									tempInt1 = scriptIntValues[isp];
									createdComponent.parent = ComponentList.getCreatedComponent(tempInt1, j);
									continue;
								}
								if (opcode == CC_SETDRAGRENDERBEHAVIOR) {
									isp--; // Decrement then read
									createdComponent.dragRenderBehavior = scriptIntValues[isp] == 1;
									continue;
								}
								if (opcode == CC_SETDRAGDEADZONE) {
									isp--; // Decrement then read
									createdComponent.dragDeadzone = scriptIntValues[isp];
									continue;
								}
								if (opcode == CC_SETDRAGDEADTIME) {
									isp--; // Decrement then read
									createdComponent.dragDeadtime = scriptIntValues[isp];
									continue;
								}
								if (opcode == CC_SETOPTIONBASE) {
									ssp--;
									createdComponent.optionBase = scriptStringValues[ssp];
									continue;
								}
								if (opcode == CC_SETOPTIONCIRCUMFIX) {
									ssp--;
									createdComponent.optionCircumfix = scriptStringValues[ssp];
									continue;
								}
								if (opcode == CC_CLEAROPS) {
									createdComponent.ops = null;
									continue;
								}
								if (opcode == CC_SETCOMPONENTINTS) {
									isp--; // Decrement then read
									createdComponent.anInt484 = scriptIntValues[isp];
									isp--; // Decrement then read
									createdComponent.anInt499 = scriptIntValues[isp];
									continue;
								}
								if (opcode == CC_SETCOMPONENTPROPERTY) {
									isp--; // Decrement then read
									tempInt1 = scriptIntValues[isp];
									isp--; // Decrement then read
									j = scriptIntValues[isp];
									if (j >= 1 && j <= 10) {
										createdComponent.method477(j - 1, tempInt1);
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
										isp--; // Decrement then read
										createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
									}
									@Pc(12937) int[] triggerArray = null;
									ssp--;
									chatTypedLowercase = scriptStringValues[ssp];
									if (chatTypedLowercase.length() > 0 && chatTypedLowercase.charAt(chatTypedLowercase.length() - 1) == 89) {
										isp--; // Decrement then read
										i = scriptIntValues[isp];
										if (i > 0) {
											triggerArray = new int[i];
											while (i-- > 0) {
												isp--; // Decrement then read
												triggerArray[i] = scriptIntValues[isp];
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
											isp--; // Decrement then read
											arguments[start] = Integer.valueOf(scriptIntValues[isp]);
										}
									}
									isp--; // Decrement then read
									start = scriptIntValues[isp];
									if (start == -1) {
										arguments = null;
									} else {
										arguments[0] = Integer.valueOf(start);
									}
									createdComponent.interactive = true;
									if (opcode == CC_SETONCLICKREPEAT) {
										createdComponent.onClickRepeat = arguments;
									} else if (opcode == CC_SETONHOLD) {
										createdComponent.onHold = arguments;
									} else if (opcode == CC_SETONRELEASE) {
										createdComponent.onRelease = arguments;
									} else if (opcode == CC_SETONMOUSEOVER) {
										createdComponent.onMouseOver = arguments;
									} else if (opcode == CC_SETONMOUSELEAVE) {
										createdComponent.onMouseLeave = arguments;
									} else if (opcode == CC_SETONDRAGSTART) {
										createdComponent.onDragStart = arguments;
									} else if (opcode == CC_SETONUSEWITH) {
										createdComponent.onUseWith = arguments;
									} else if (opcode == CC_SETONVARPTRANSMIT) {
										createdComponent.varpTriggers = triggerArray;
										createdComponent.onVarpTransmit = arguments;
									} else if (opcode == CC_SETONTIMER) {
										createdComponent.onTimer = arguments;
									} else if (opcode == CC_SETONOPTIONCLICK) {
										createdComponent.onOptionClick = arguments;
									} else if (opcode == CC_SETONDRAGRELEASE) {
										createdComponent.onDragRelease = arguments;
									} else if (opcode == CC_SETONDRAG) {
										createdComponent.onDrag = arguments;
									} else if (opcode == CC_SETONMOUSEREPEAT) {
										createdComponent.onMouseRepeat = arguments;
									} else if (opcode == CC_SETONINVTRANSMIT) {
										createdComponent.inventoryTriggers = triggerArray;
										createdComponent.onInvTransmit = arguments;
									} else if (opcode == CC_SETONSTATTRANSMIT) {
										createdComponent.statTriggers = triggerArray;
										createdComponent.onStatTransmit = arguments;
									} else if (opcode == CC_SETONUSE) {
										createdComponent.onUse = arguments;
									} else if (opcode == CC_SETONSCROLL) {
										createdComponent.onScroll = arguments;
									} else if (opcode == CC_SETONMSG) {
										createdComponent.onMsg = arguments;
									} else if (opcode == CC_SETONKEY) {
										createdComponent.onKey = arguments;
									} else if (opcode == CC_SETONFRIENDTRANSMIT) {
										createdComponent.onFriendTransmit = arguments;
									} else if (opcode == CC_SETONCLANTRANSMIT) {
										createdComponent.onClanTransmit = arguments;
									} else if (opcode == CC_SETONMISCTRANSMIT) {
										createdComponent.onMiscTransmit = arguments;
									} else if (opcode == CC_SETONDIALOGABORT) {
										createdComponent.onDialogAbort = arguments;
									} else if (opcode == CC_SETONCOMPONENTSOPENCLOSE) {
										createdComponent.onComponentsOpenClose = arguments;
									} else if (opcode == CC_SETONSTOCKTRANSMIT) {
										createdComponent.onStockTransmit = arguments;
									} else if (opcode == CC_SETONMINIMAPUNLOCK) {
										createdComponent.onMinimapUnlock = arguments;
									} else if (opcode == CC_SETONRESIZE) {
										createdComponent.onResize = arguments;
									} else if (opcode == CC_SETONVARCTRANSMIT) {
										createdComponent.onVarcTransmit = arguments;
										createdComponent.varcTriggers = triggerArray;
									} else if (opcode == CC_SETONVARCSTRTRANSMIT) {
										createdComponent.varcstrTriggers = triggerArray;
										createdComponent.onVarcstrTransmit = arguments;
									}
									continue;
								}
								if (opcode < 1600) {
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
									if (opcode == CC_GETX) {
										// cc_getx
										scriptIntValues[isp++] = createdComponent.x;
										continue;
									}
									if (opcode == CC_GETY) {
										// cc_gety
										scriptIntValues[isp++] = createdComponent.y;
										continue;
									}
									if (opcode == CC_GETWIDTH) {
										// cc_getwidth
										scriptIntValues[isp++] = createdComponent.width;
										continue;
									}
									if (opcode == CC_GETHEIGHT) {
										// cc_getheight
										scriptIntValues[isp++] = createdComponent.height;
										continue;
									}
									if (opcode == CC_GETHIDE) {
										// cc_gethide
										scriptIntValues[isp++] = createdComponent.hidden ? 1 : 0;
										continue;
									}
									if (opcode == CC_GETLAYER) {
										// set_getlayer
										scriptIntValues[isp++] = createdComponent.overlayer;
										continue;
									}
								} else if (opcode < 1700) {
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
									if (opcode == CC_GETSCROLLX) {
										// cc_getscrollx
										scriptIntValues[isp++] = createdComponent.scrollX;
										continue;
									}
									if (opcode == CC_GETSCROLLY) {
										// cc_getscrolly
										scriptIntValues[isp++] = createdComponent.scrollY;
										continue;
									}
									if (opcode == CC_GETTEXT) {
										scriptStringValues[ssp++] = createdComponent.text;
										continue;
									}
									if (opcode == CC_GETSCROLLWIDTH) {
										scriptIntValues[isp++] = createdComponent.scrollMaxH;
										continue;
									}
									if (opcode == CC_GETSCROLLHEIGHT) {
										scriptIntValues[isp++] = createdComponent.scrollMaxV;
										continue;
									}
									if (opcode == CC_GETZOOM) {
										scriptIntValues[isp++] = createdComponent.modelZoom;
										continue;
									}
									if (opcode == CC_GETMODELXANGLE) {
										scriptIntValues[isp++] = createdComponent.modelXAngle;
										continue;
									}
									if (opcode == CC_GETMODELYOFFSET) {
										scriptIntValues[isp++] = createdComponent.modelYOffset;
										continue;
									}
									if (opcode == CC_GETMODELYANGLE) {
										scriptIntValues[isp++] = createdComponent.modelYAngle;
										continue;
									}
									if (opcode == CC_GETTRANS) {
										scriptIntValues[isp++] = createdComponent.alpha;
										continue;
									}
									if (opcode == CC_GETMODELXOFFSET) {
										scriptIntValues[isp++] = createdComponent.modelXOffset;
										continue;
									}
									if (opcode == CC_GETMODELZOFFSET) {
										scriptIntValues[isp++] = createdComponent.modelZOffset;
										continue;
									}
									if (opcode == CC_GETGRAPHIC) {
										scriptIntValues[isp++] = createdComponent.spriteId;
										continue;
									}
								} else if (opcode < 1800) {
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
									if (opcode == CC_GETINVOBJECT) {
										scriptIntValues[isp++] = createdComponent.objId;
										continue;
									}
									if (opcode == CC_GETINVCOUNT) {
										if (createdComponent.objId == -1) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = createdComponent.objCount;
										}
										continue;
									}
									if (opcode == CC_GETID) {
										scriptIntValues[isp++] = createdComponent.createdComponentId;
										continue;
									}
								} else if (opcode < 1900) {
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
									if (opcode == CC_GETTARGETMASK) {
										scriptIntValues[isp++] = ComponentList.getServerActiveProperties(createdComponent).getTargetMask();
										continue;
									}
									if (opcode == CC_GETOP) {
										isp--; // Decrement then read
										tempInt1 = scriptIntValues[isp];
										tempInt1--;
										if (createdComponent.ops != null && tempInt1 < createdComponent.ops.length && createdComponent.ops[tempInt1] != null) {
											scriptStringValues[ssp++] = createdComponent.ops[tempInt1];
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == CC_GETOPBASE) {
										if (createdComponent.optionBase == null) {
											scriptStringValues[ssp++] = EMPTY_STRING;
										} else {
											scriptStringValues[ssp++] = createdComponent.optionBase;
										}
										continue;
									}
								} else if (opcode < 2600) {
									isp--; // Decrement then read
									createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
									if (opcode == IF_GETX) {
										// if_getx
										scriptIntValues[isp++] = createdComponent.x;
										continue;
									}
									if (opcode == IF_GETY) {
										// if_gety
										scriptIntValues[isp++] = createdComponent.y;
										continue;
									}
									if (opcode == IF_GETWIDTH) {
										// if_getwidth
										scriptIntValues[isp++] = createdComponent.width;
										continue;
									}
									if (opcode == IF_GETHEIGHT) {
										// if_getheight
										scriptIntValues[isp++] = createdComponent.height;
										continue;
									}
									if (opcode == IF_GETHIDE) {
										// if_gethide
										scriptIntValues[isp++] = createdComponent.hidden ? 1 : 0;
										continue;
									}
									if (opcode == IF_GETLAYER) {
										// if_getlayer
										scriptIntValues[isp++] = createdComponent.overlayer;
										continue;
									}
								} else if (opcode < 2700) {
									isp--; // Decrement then read
									createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
									if (opcode == IF_GETSCROLLX) {
										// if_getscrollx
										scriptIntValues[isp++] = createdComponent.scrollX;
										continue;
									}
									if (opcode == IF_GETSCROLLY) {
										// if_getscrolly
										scriptIntValues[isp++] = createdComponent.scrollY;
										continue;
									}
									if (opcode == IF_GETTEXT) {
										scriptStringValues[ssp++] = createdComponent.text;
										continue;
									}
									if (opcode == IF_GETSCROLLWIDTH) {
										scriptIntValues[isp++] = createdComponent.scrollMaxH;
										continue;
									}
									if (opcode == IF_GETSCROLLHEIGHT) {
										scriptIntValues[isp++] = createdComponent.scrollMaxV;
										continue;
									}
									if (opcode == IF_GETZOOM) {
										scriptIntValues[isp++] = createdComponent.modelZoom;
										continue;
									}
									if (opcode == IF_GETMODELXANGLE) {
										scriptIntValues[isp++] = createdComponent.modelXAngle;
										continue;
									}
									if (opcode == IF_GETMODELYOFFSET) {
										scriptIntValues[isp++] = createdComponent.modelYOffset;
										continue;
									}
									if (opcode == IF_GETMODELYANGLE) {
										scriptIntValues[isp++] = createdComponent.modelYAngle;
										continue;
									}
									if (opcode == IF_GETTRANS) {
										scriptIntValues[isp++] = createdComponent.alpha;
										continue;
									}
									if (opcode == IF_GETMODELXOFFSET) {
										scriptIntValues[isp++] = createdComponent.modelXOffset;
										continue;
									}
									if (opcode == IF_GETMODELZOFFSET) {
										scriptIntValues[isp++] = createdComponent.modelZOffset;
										continue;
									}
									if (opcode == IF_GETGRAPHIC) {
										scriptIntValues[isp++] = createdComponent.spriteId;
										continue;
									}
								} else if (opcode < 2800) {
									if (opcode == IF_GETINVOBJECT) {
										isp--; // Decrement then read
										createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
										scriptIntValues[isp++] = createdComponent.objId;
										continue;
									}
									if (opcode == IF_GETINVCOUNT) {
										isp--; // Decrement then read
										createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
										if (createdComponent.objId == -1) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = createdComponent.objCount;
										}
										continue;
									}
									if (opcode == IF_HASSUB) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										@Pc(12566) SubInterface openSubInterface = (SubInterface) ComponentList.openInterfaces.get((long) tempInt2);
										if (openSubInterface == null) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = 1;
										}
										continue;
									}
									if (opcode == IF_GETTOTALSUBCOMPONENTS) {
										isp--; // Decrement then read
										createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
										if (createdComponent.createdComponents == null) {
											scriptIntValues[isp++] = 0;
											continue;
										}
										tempInt1 = createdComponent.createdComponents.length;
										for (j = 0; j < createdComponent.createdComponents.length; j++) {
											if (createdComponent.createdComponents[j] == null) {
												tempInt1 = j;
												break;
											}
										}
										scriptIntValues[isp++] = tempInt1;
										continue;
									}
									if (opcode == 2704 || opcode == 2705) {
										isp -= 2;
										tempInt2 = scriptIntValues[isp];
										tempInt1 = scriptIntValues[isp + 1];
										@Pc(12663) SubInterface targetSubInterface = (SubInterface) ComponentList.openInterfaces.get((long) tempInt2);
										if (targetSubInterface != null && targetSubInterface.interfaceId == tempInt1) {
											scriptIntValues[isp++] = 1;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
								} else if (opcode < 2900) {
									isp--; // Decrement then read
									createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
									if (opcode == IF_GETTARGETMASK) {
										scriptIntValues[isp++] = ComponentList.getServerActiveProperties(createdComponent).getTargetMask();
										continue;
									}
									if (opcode == IF_GETOP) {
										isp--; // Decrement then read
										tempInt1 = scriptIntValues[isp];
										tempInt1--;
										if (createdComponent.ops != null && createdComponent.ops.length > tempInt1 && createdComponent.ops[tempInt1] != null) {
											scriptStringValues[ssp++] = createdComponent.ops[tempInt1];
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == IF_GETOPBASE) {
										if (createdComponent.optionBase == null) {
											scriptStringValues[ssp++] = EMPTY_STRING;
										} else {
											scriptStringValues[ssp++] = createdComponent.optionBase;
										}
										continue;
									}
								} else if (opcode < 3200) {
									if (opcode == MESSAGE) {
										// mes
										ssp--;
										tempString1 = scriptStringValues[ssp];
										Chat.addMessage(EMPTY_STRING, 0, tempString1);
										continue;
									}
									if (opcode == ANIM) {
										// anim
										isp -= 2;
										Player.animate(scriptIntValues[isp + 1], scriptIntValues[isp], PlayerList.self);
										continue;
									}
									if (opcode == IF_CLOSE) {
										ComponentList.closeModal();
										continue;
									}
									if (opcode == IF_ON_COMPONENT) {
										ssp--;
										tempString1 = scriptStringValues[ssp];
										tempInt1 = 0;
										if (tempString1.isInt()) {
											tempInt1 = tempString1.parseInt();
										}
										Protocol.outboundBuffer.pIsaac1(ClientProt.IF_ON_COMPONENT);
										Protocol.outboundBuffer.p4(tempInt1);
										continue;
									}
									if (opcode == IF_ON_COMPONENT2) {
										ssp--;
										tempString1 = scriptStringValues[ssp];
										Protocol.outboundBuffer.pIsaac1(ClientProt.IF_ON_COMPONENT2);
										Protocol.outboundBuffer.p8(tempString1.encode37());
										continue;
									}
									if (opcode == IF_ON_COMPONENT3) {
										ssp--;
										tempString1 = scriptStringValues[ssp];
										Protocol.outboundBuffer.pIsaac1(ClientProt.IF_ON_COMPONENT3);
										Protocol.outboundBuffer.p1(tempString1.length() + 1);
										Protocol.outboundBuffer.pjstr(tempString1);
										continue;
									}
									if (opcode == CLICK_PLAYER_OPTION) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										ssp--;
										chatTypedLowercase = scriptStringValues[ssp];
										ClientProt.clickPlayerOption(tempInt2, chatTypedLowercase);
										continue;
									}
									if (opcode == STARTDRAG) {
										isp -= 3;
										tempInt1 = scriptIntValues[isp + 1];
										tempInt2 = scriptIntValues[isp];
										j = scriptIntValues[isp + 2];
										component = ComponentList.getComponent(j);
										startComponentDrag(tempInt1, tempInt2, component);
										continue;
									}
									if (opcode == STARTUSINGDRAG) {
										isp -= 2;
										tempInt2 = scriptIntValues[isp];
										foundComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
										tempInt1 = scriptIntValues[isp + 1];
										startComponentDrag(tempInt1, tempInt2, foundComponent);
										continue;
									}
									if (opcode == IF_ON_COMPONENT4) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										Protocol.outboundBuffer.pIsaac1(ClientProt.IF_ON_COMPONENT4);
										Protocol.outboundBuffer.p2(tempInt2);
										continue;
									}
								} else if (opcode < 3300) {
									if (opcode == SOUND_SYNTH) {
										// sound_synth
										isp -= 3;
										SoundPlayer.play(scriptIntValues[isp + 1], scriptIntValues[isp], scriptIntValues[isp + 2]);
										continue;
									}
									if (opcode == SOUND_SONG) {
										// sound_song
										isp--; // Decrement then read
										MusicPlayer.playSong(scriptIntValues[isp]);
										continue;
									}
									if (opcode == SOUND_JINGLE) {
										// sound_jingle
										isp -= 2;
										MusicPlayer.playJingle(scriptIntValues[isp + 1], scriptIntValues[isp]);
										continue;
									}
								} else if (opcode < 3400) {
									if (opcode == CLIENTCLOCK) {
										// clientclock
										scriptIntValues[isp++] = Client.loop;
										continue;
									}
									if (opcode == INV_GETOBJ) {
										isp -= 2;
										tempInt2 = scriptIntValues[isp];
										tempInt1 = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = Inv.getItemType(tempInt2, tempInt1);
										continue;
									}
									if (opcode == INV_GETNUM) {
										isp -= 2;
										tempInt1 = scriptIntValues[isp + 1];
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = Inv.getItemCount(tempInt2, tempInt1);
										continue;
									}
									if (opcode == INV_TOTAL) {
										isp -= 2;
										tempInt1 = scriptIntValues[isp + 1];
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = Inv.getSlotTotal(tempInt2, tempInt1);
										continue;
									}
									if (opcode == INV_SIZE) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = InvTypeList.get(tempInt2).size;
										continue;
									}
									if (opcode == STAT) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = PlayerSkillXpTable.boostedLevels[tempInt2];
										continue;
									}
									if (opcode == STAT_BASE) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = PlayerSkillXpTable.baseLevels[tempInt2];
										continue;
									}
									if (opcode == STAT_XP) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = PlayerSkillXpTable.experience[tempInt2];
										continue;
									}
									if (opcode == COORD) {
                                        tempInt2 = Player.currentLevel;
										tempInt1 = Camera.sceneBaseTileX + (PlayerList.self.xFine >> 7); // Convert fine coords to tiles
										j = (PlayerList.self.zFine >> 7) + Camera.sceneBaseTileZ;
										scriptIntValues[isp++] = (tempInt2 << 28) - (-(tempInt1 << 14) - j);
										continue;
									}
									if (opcode == COORDX) {
                                        isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = tempInt2 >> 14 & 0x3FFF;
										continue;
									}
									if (opcode == COORDY) {
                                        isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = tempInt2 >> 28;
										continue;
									}
									if (opcode == COORDZ) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = tempInt2 & 0x3FFF;
										continue;
									}
									if (opcode == ISMEMBER) {
										scriptIntValues[isp++] = LoginManager.membersWorld ? 1 : 0;
										continue;
									}
									if (opcode == INV_GETOBJ_OFFSET) {
                                        // inv_getobj
										isp -= 2;
										tempInt2 = scriptIntValues[isp] + 32768; // Add 32768 for bank inventory offset
										tempInt1 = scriptIntValues[isp + 1]; // Slot index
										scriptIntValues[isp++] = Inv.getItemType(tempInt2, tempInt1);
										continue;
									}
									if (opcode == INV_GETNUM_OFFSET) {
                                        // inv_getnum
										isp -= 2;
										tempInt2 = scriptIntValues[isp] + 32768; // Add 32768 for bank inventory offset
                                        tempInt1 = scriptIntValues[isp + 1]; // Slot index
										scriptIntValues[isp++] = Inv.getItemCount(tempInt2, tempInt1);
										continue;
									}
									if (opcode == INV_TOTAL_OFFSET) {
                                        // inv_total
										isp -= 2;
										tempInt2 = scriptIntValues[isp] + 32768; // Add 32768 for bank inventory offset
										tempInt1 = scriptIntValues[isp + 1]; // Item type to count
										scriptIntValues[isp++] = Inv.getSlotTotal(tempInt2, tempInt1);
										continue;
									}
									if (opcode == MODLEVEL) {
                                        // modlevel
										if (LoginManager.staffModLevel < 2) {
											scriptIntValues[isp++] = 0; // Hide non-staff mod levels
										} else {
											scriptIntValues[isp++] = LoginManager.staffModLevel;
										}
										continue;
									}
									if (opcode == RUNTIMER) {
										scriptIntValues[isp++] = Player.systemUpdateTimer;
										continue;
									}
									if (opcode == WORLD) {
										scriptIntValues[isp++] = Player.worldId;
										continue;
									}
									if (opcode == ENERGYSTAT) {
										scriptIntValues[isp++] = Player.runEnergy;
										continue;
									}
									if (opcode == WEIGHT) {
										scriptIntValues[isp++] = Player.weightCarried;
										continue;
									}
									if (opcode == PLAYERMOD) {
                                        // playermod
                                        // Returns 1 if PMod
										if (LoginManager.playerModLevel >= 5 && LoginManager.playerModLevel <= 9) {
											scriptIntValues[isp++] = 1;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == PLAYERMODLEVEL) {
                                        // playermodlevel
                                        // Range 5-9 for different PMod tiers
										if (LoginManager.playerModLevel >= 5 && LoginManager.playerModLevel <= 9) {
											scriptIntValues[isp++] = LoginManager.playerModLevel;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == PLAYERMEMBER) {
										scriptIntValues[isp++] = LoginManager.playerMember ? 1 : 0;
										continue;
									}
									if (opcode == COMBATLEVEL) {
										scriptIntValues[isp++] = PlayerList.self.combatLevel;
										continue;
									}
									if (opcode == GENDER) {
										scriptIntValues[isp++] = PlayerList.self.appearance.gender ? 1 : 0;
										continue;
									}
									if (opcode == PLAYERUNDERAGECHAT) {
										scriptIntValues[isp++] = LoginManager.playerUnderage && !LoginManager.parentalChatConsent ? 1 : 0;
										continue;
									}
									if (opcode == QUICKCHATWORLD) {
										scriptIntValues[isp++] = LoginManager.worldQuickChat ? 1 : 0;
										continue;
									}
									if (opcode == INV_FREESPACE) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = Inv.getFreeSpace(tempInt2);
										continue;
									}
									if (opcode == INV_TOTALPARAM) {
										isp -= 2;
										tempInt1 = scriptIntValues[isp + 1];
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = Inv.getTotalParam(false, tempInt2, tempInt1);
										continue;
									}
									if (opcode == INV_TOTALPARAM_BANKING) {
										isp -= 2;
										tempInt2 = scriptIntValues[isp];
										tempInt1 = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = Inv.getTotalParam(true, tempInt2, tempInt1);
										continue;
									}
									if (opcode == UNKNOWN_INT_GETTER) {
										scriptIntValues[isp++] = LoginManager.anInt39;
										continue;
									}
									if (opcode == LANGUAGE) {
										scriptIntValues[isp++] = Client.language;
										continue;
									}
									if (opcode == BUILDCOORD) {
										isp -= 4;
										tempInt1 = scriptIntValues[isp + 1]; // X tile coordinate
										tempInt2 = scriptIntValues[isp]; // Z tile coordinate
										tempInt2 += tempInt1 << 14;
										i = scriptIntValues[isp + 3]; // Local X offset within tile
										j = scriptIntValues[isp + 2]; // Level/height
										tempInt2 += j << 28;
										tempInt2 += i;
										scriptIntValues[isp++] = tempInt2;
										continue;
									}
									if (opcode == AFFILIATE) {
										scriptIntValues[isp++] = Client.affiliate;
										continue;
									}
								} else if (opcode < 3500) {
									@Pc(3422) EnumType type;
									if (opcode == ENUM) {
										isp -= 2;
										tempInt2 = scriptIntValues[isp];
										tempInt1 = scriptIntValues[isp + 1];
										type = EnumTypeList.get(tempInt2);
										if (type.valueType == 115) {
										}
										scriptStringValues[ssp++] = type.getValueString(tempInt1);
										continue;
									}
									if (opcode == ENUM_GETOUTPUTCOUNT) {
										isp -= 4;
										tempInt2 = scriptIntValues[isp];
										tempInt1 = scriptIntValues[isp + 1];
										i = scriptIntValues[isp + 3];
										j = scriptIntValues[isp + 2];
										@Pc(3469) EnumType keyEnumType = EnumTypeList.get(j);
										if (keyEnumType.keyType == tempInt2 && keyEnumType.valueType == tempInt1) {
											if (tempInt1 == 115) {
												scriptStringValues[ssp++] = keyEnumType.getValueString(i);
											} else {
												scriptIntValues[isp++] = keyEnumType.getValueInt(i);
											}
											continue;
										}
										throw new RuntimeException("C3408-1");
									}
									if (opcode == ENUM_HASOUTPUT) {
										isp -= 3;
										tempInt1 = scriptIntValues[isp + 1];
										j = scriptIntValues[isp + 2];
										tempInt2 = scriptIntValues[isp];
										if (tempInt1 == -1) {
											throw new RuntimeException("C3409-2");
										}
										@Pc(3549) EnumType valueEnumType = EnumTypeList.get(tempInt1);
										if (valueEnumType.valueType != tempInt2) {
											throw new RuntimeException("C3409-1");
										}
										scriptIntValues[isp++] = valueEnumType.containsValue(j) ? 1 : 0;
										continue;
									}
									if (opcode == ENUM_HASOUTPUTSTRING) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										ssp--;
										chatTypedLowercase = scriptStringValues[ssp];
										if (tempInt2 == -1) {
											throw new RuntimeException("C3410-2");
										}
										type = EnumTypeList.get(tempInt2);
										if (type.valueType != 115) {
											throw new RuntimeException("C3410-1");
										}
										scriptIntValues[isp++] = type.method3086(chatTypedLowercase) ? 1 : 0;
										continue;
									}
									if (opcode == ENUM_GETSIZE) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										@Pc(3645) EnumType outputEnumType = EnumTypeList.get(tempInt2);
										scriptIntValues[isp++] = outputEnumType.table.length();
										continue;
									}
								} else if (opcode < 3700) {
									if (opcode == FRIENDCOUNT) {
										if (FriendList.state == 0) {
											scriptIntValues[isp++] = -2;
										} else if (FriendList.state == 1) {
											scriptIntValues[isp++] = -1;
										} else {
											scriptIntValues[isp++] = FriendList.friendCount;
										}
										continue;
									}
									if (opcode == FRIENDGETNAME) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										if (FriendList.state == 2 && tempInt2 < FriendList.friendCount) {
											scriptStringValues[ssp++] = FriendList.friendUsernames[tempInt2];
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == FRIENDGETWORLD) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										if (FriendList.state == 2 && FriendList.friendCount > tempInt2) {
											scriptIntValues[isp++] = FriendList.friendWorlds[tempInt2];
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == FRIENDGETRANK) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										if (FriendList.state == 2 && FriendList.friendCount > tempInt2) {
											scriptIntValues[isp++] = FriendList.ranks[tempInt2];
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == FRIENDSETRANK) {
										isp--; // Decrement then read
										tempInt1 = scriptIntValues[isp];
										ssp--;
										tempString1 = scriptStringValues[ssp];
										FriendList.setRank(tempString1, tempInt1);
										continue;
									}
									if (opcode == FRIENDADD) {
										ssp--;
										tempString1 = scriptStringValues[ssp];
										FriendList.addFriend(tempString1.encode37());
										continue;
									}
									if (opcode == FRIENDDEL) {
										ssp--;
										tempString1 = scriptStringValues[ssp];
										FriendList.removeFriend(tempString1.encode37());
										continue;
									}
									if (opcode == IGNOREAD) {
										ssp--;
										tempString1 = scriptStringValues[ssp];
										IgnoreList.addIgnore(tempString1.encode37());
										continue;
									}
									if (opcode == IGNOREDEL) {
										ssp--;
										tempString1 = scriptStringValues[ssp];
										IgnoreList.remove(tempString1.encode37());
										continue;
									}
									if (opcode == FRIENDTEST) {
										ssp--;
										tempString1 = scriptStringValues[ssp];
										if (tempString1.startsWith(SPRITE_TAG_0) || tempString1.startsWith(SPRITE_TAG_1)) {
											tempString1 = tempString1.substring(7);
										}
										scriptIntValues[isp++] = FriendList.contains(tempString1) ? 1 : 0;
										continue;
									}
									if (opcode == FRIENDGETWORLDNAME) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										if (FriendList.state == 2 && FriendList.friendCount > tempInt2) {
											scriptStringValues[ssp++] = FriendList.worldNames[tempInt2];
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == CLANCHATGETNAME) {
										if (ClanChat.name == null) {
											scriptStringValues[ssp++] = EMPTY_STRING;
										} else {
											scriptStringValues[ssp++] = ClanChat.name.toTitleCase();
										}
										continue;
									}
									if (opcode == CLANCHATGETSIZE) {
										if (ClanChat.name == null) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = ClanChat.size;
										}
										continue;
									}
									if (opcode == CLANCHATGETUSERNAME) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										if (ClanChat.name != null && ClanChat.size > tempInt2) {
											scriptStringValues[ssp++] = ClanChat.members[tempInt2].username.toTitleCase();
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == CLANCHATGETUSERWORLD) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										if (ClanChat.name != null && tempInt2 < ClanChat.size) {
											scriptIntValues[isp++] = ClanChat.members[tempInt2].world;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == CLANCHATGETUSERRANK) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										if (ClanChat.name != null && ClanChat.size > tempInt2) {
											scriptIntValues[isp++] = ClanChat.members[tempInt2].rank;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == CLANCHATMINKICK) {
										scriptIntValues[isp++] = ClanChat.minKick;
										continue;
									}
									if (opcode == CLANCHATKICK) {
										ssp--;
										tempString1 = scriptStringValues[ssp];
										ClanChat.kick(tempString1);
										continue;
									}
									if (opcode == CLANCHATRANK) {
										scriptIntValues[isp++] = ClanChat.rank;
										continue;
									}
									if (opcode == CLANCHATJOIN) {
										ssp--;
										tempString1 = scriptStringValues[ssp];
										ClanChat.join(tempString1.encode37());
										continue;
									}
									if (opcode == CLANCHATLEAVE) {
										ClanChat.leave();
										continue;
									}
									if (opcode == IGNORECOUNT) {
										if (FriendList.state == 0) {
											scriptIntValues[isp++] = -1;
										} else {
											scriptIntValues[isp++] = IgnoreList.ignoreCount;
										}
										continue;
									}
									if (opcode == IGNOREGETNAME) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										if (FriendList.state != 0 && IgnoreList.ignoreCount > tempInt2) {
											scriptStringValues[ssp++] = Base37.fromBase37(IgnoreList.encodedIgnores[tempInt2]).toTitleCase();
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == IGNORETEST) {
										ssp--;
										tempString1 = scriptStringValues[ssp];
										if (tempString1.startsWith(SPRITE_TAG_0) || tempString1.startsWith(SPRITE_TAG_1)) {
											tempString1 = tempString1.substring(7);
										}
										scriptIntValues[isp++] = IgnoreList.contains(tempString1) ? 1 : 0;
										continue;
									}
									if (opcode == CLANCHATISSELFINCHANNEL) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										if (ClanChat.members != null && ClanChat.size > tempInt2 && ClanChat.members[tempInt2].username.equalsIgnoreCase(PlayerList.self.username)) {
											scriptIntValues[isp++] = 1;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == CLANCHATGETOWNER) {
										if (ClanChat.owner == null) {
											scriptStringValues[ssp++] = EMPTY_STRING;
										} else {
											scriptStringValues[ssp++] = ClanChat.owner.toTitleCase();
										}
										continue;
									}
									if (opcode == CLANCHATGETWORLDNAME) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										if (ClanChat.name != null && ClanChat.size > tempInt2) {
											scriptStringValues[ssp++] = ClanChat.members[tempInt2].worldName;
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == FRIENDGETGAME) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										if (FriendList.state == 2 && tempInt2 >= 0 && tempInt2 < FriendList.friendCount) {
											scriptIntValues[isp++] = FriendList.friendGame[tempInt2] ? 1 : 0;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == FRIENDGETINDEX) {
										ssp--;
										tempString1 = scriptStringValues[ssp];
										if (tempString1.startsWith(SPRITE_TAG_0) || tempString1.startsWith(SPRITE_TAG_1)) {
											tempString1 = tempString1.substring(7);
										}
										scriptIntValues[isp++] = FriendList.indexOf(tempString1);
										continue;
									}
									if (opcode == COUNTRY) {
										scriptIntValues[isp++] = Client.country;
										continue;
									}
								} else if (opcode < 4000) {
									if (opcode == STOCKMARKET_GETOFFERTYPE) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = StockMarketManager.offers[tempInt2].getType();
										continue;
									}
									if (opcode == STOCKMARKET_GETOFFERITEM) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = StockMarketManager.offers[tempInt2].item;
										continue;
									}
									if (opcode == STOCKMARKET_GETOFFERPRICE) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = StockMarketManager.offers[tempInt2].price;
										continue;
									}
									if (opcode == STOCKMARKET_GETOFFERCOUNT) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = StockMarketManager.offers[tempInt2].count;
										continue;
									}
									if (opcode == STOCKMARKET_GETOFFERCOMPLETEDCOUNT) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = StockMarketManager.offers[tempInt2].completedCount;
										continue;
									}
									if (opcode == STOCKMARKET_GETOFFERCOMPLETEDGOLD) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = StockMarketManager.offers[tempInt2].completedGold;
										continue;
									}
									if (opcode == STOCKMARKET_ISOFFEREMPTY) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										tempInt1 = StockMarketManager.offers[tempInt2].getStatus();
										scriptIntValues[isp++] = tempInt1 == 0 ? 1 : 0;
										continue;
									}
									if (opcode == STOCKMARKET_ISOFFERCOMPLETING) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										tempInt1 = StockMarketManager.offers[tempInt2].getStatus();
										scriptIntValues[isp++] = tempInt1 == 2 ? 1 : 0;
										continue;
									}
									if (opcode == STOCKMARKET_ISOFFERFINISHED) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										tempInt1 = StockMarketManager.offers[tempInt2].getStatus();
										scriptIntValues[isp++] = tempInt1 == 5 ? 1 : 0;
										continue;
									}
									if (opcode == STOCKMARKET_ISOFFERACTIVE) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										tempInt1 = StockMarketManager.offers[tempInt2].getStatus();
										scriptIntValues[isp++] = tempInt1 == 1 ? 1 : 0;
										continue;
									}
								} else if (opcode < 4100) {
									if (opcode == ADD) {
										// add
										isp -= 2;
										tempInt2 = scriptIntValues[isp];
										tempInt1 = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = tempInt1 + tempInt2;
										continue;
									}
									if (opcode == SUB) {
										// sub
										isp -= 2;
										tempInt2 = scriptIntValues[isp];
										tempInt1 = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = tempInt2 - tempInt1;
										continue;
									}
									if (opcode == MULTIPLY) {
										// multiply
										isp -= 2;
										tempInt2 = scriptIntValues[isp];
										tempInt1 = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = tempInt1 * tempInt2;
										continue;
									}
									if (opcode == DIVIDE) {
										// divide
										isp -= 2;
										tempInt2 = scriptIntValues[isp];
										tempInt1 = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = tempInt2 / tempInt1;
										continue;
									}
									if (opcode == RANDOM) {
										// random
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = (int) ((double) tempInt2 * Math.random());
										continue;
									}
									if (opcode == RANDOMINC) {
										// randominc
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = (int) (Math.random() * (double) (tempInt2 + 1));
										continue;
									}
									if (opcode == INTERPOLATE) {
										// interpolate
										isp -= 5;
										tempInt2 = scriptIntValues[isp];
										tempInt1 = scriptIntValues[isp + 1];
										i = scriptIntValues[isp + 3];
										j = scriptIntValues[isp + 2];
										start = scriptIntValues[isp + 4];
										scriptIntValues[isp++] = (tempInt1 - tempInt2) * (start + -j) / (i - j) + tempInt2;
										continue;
									}
									@Pc(4899) long local4899; // Something date??
									@Pc(4892) long local4892; // Something date??
									if (opcode == ADDPERCENT) {
										// addpercent
										isp -= 2;
										local4892 = scriptIntValues[isp];
										local4899 = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = (int) (local4892 * local4899 / 100L + local4892);
										continue;
									}
									if (opcode == SETBIT) {
										// setbit
										isp -= 2;
										tempInt2 = scriptIntValues[isp];
										tempInt1 = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = tempInt2 | 0x1 << tempInt1;
										continue;
									}
									if (opcode == CLEARBIT) {
										// clearbit
										isp -= 2;
										tempInt2 = scriptIntValues[isp];
										tempInt1 = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = -(0x1 << tempInt1) - 1 & tempInt2;
										continue;
									}
									if (opcode == TESTBIT) {
										// testbit
										isp -= 2;
										tempInt2 = scriptIntValues[isp]; // The integer to test
										tempInt1 = scriptIntValues[isp + 1]; // Which bit to test
										scriptIntValues[isp++] = (tempInt2 & 0x1 << tempInt1) == 0 ? 0 : 1;
										continue;
									}
									if (opcode == MODULO) {
										// modulo
										isp -= 2;
										tempInt1 = scriptIntValues[isp + 1];
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = tempInt2 % tempInt1;
										continue;
									}
									if (opcode == POW) {
										// pow
										isp -= 2;
										tempInt1 = scriptIntValues[isp + 1];
										tempInt2 = scriptIntValues[isp];
										if (tempInt2 == 0) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = (int) Math.pow((double) tempInt2, (double) tempInt1);
										}
										continue;
									}
									if (opcode == INVPOW) {
										// invpow
										isp -= 2;
										tempInt1 = scriptIntValues[isp + 1];
										tempInt2 = scriptIntValues[isp];
										if (tempInt2 == 0) {
											scriptIntValues[isp++] = 0;
										} else if (tempInt1 == 0) {
											scriptIntValues[isp++] = Integer.MAX_VALUE;
										} else {
											scriptIntValues[isp++] = (int) Math.pow((double) tempInt2, 1.0D / (double) tempInt1);
										}
										continue;
									}
									if (opcode == BITWISE_AND) {
                                        // bitwise_and
										isp -= 2;
										tempInt1 = scriptIntValues[isp + 1];
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = tempInt1 & tempInt2;
										continue;
									}
									if (opcode == BITWISE_OR) {
                                        // bitwise_or
										isp -= 2;
										tempInt2 = scriptIntValues[isp];
										tempInt1 = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = tempInt2 | tempInt1;
										continue;
									}
									if (opcode == MIN) {
                                        // min
                                        // Return smaller of two values
										isp -= 2;
										tempInt2 = scriptIntValues[isp];
										tempInt1 = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = tempInt2 < tempInt1 ? tempInt2 : tempInt1;
										continue;
									}
									if (opcode == MAX) {
                                        // max
                                        // Return larger of two values
										isp -= 2;
										tempInt1 = scriptIntValues[isp + 1];
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = tempInt1 >= tempInt2 ? tempInt1 : tempInt2;
										continue;
									}
									if (opcode == SCALE) {
                                        // scale
                                        // Used for percentage calculations without overflow
										isp -= 3;
										local4892 = scriptIntValues[isp]; // Value to scale
                                        local4899 = scriptIntValues[isp + 1]; // Divisor
										@Pc(5251) long timestamp = (long) scriptIntValues[isp + 2]; // Multiplier
										scriptIntValues[isp++] = (int) (local4892 * timestamp / local4899);
										continue;
									}
								} else if (opcode >= 4200) {
									@Pc(5294) ParamType paramType;
									if (opcode < 4300) {
										if (opcode == OC_NAME) {
                                            // oc_name
                                            // Get object/item name
											isp--; // Decrement then read
											tempInt2 = scriptIntValues[isp];
											scriptStringValues[ssp++] = ObjTypeList.get(tempInt2).name;
											continue;
										}
										@Pc(11269) ObjType invObjType;
										if (opcode == OC_OP) {
                                            // oc_op
                                            // Get ground object right-click option
											isp -= 2;
											tempInt2 = scriptIntValues[isp]; // Object type ID
											tempInt1 = scriptIntValues[isp + 1]; // Option slot
											invObjType = ObjTypeList.get(tempInt2);
											if (tempInt1 >= 1 && tempInt1 <= 5 && invObjType.op[tempInt1 - 1] != null) {
												scriptStringValues[ssp++] = invObjType.op[tempInt1 - 1];
												continue;
											}
											scriptStringValues[ssp++] = EMPTY_STRING; // No option in that slot
                                            continue;
										}
										if (opcode == OC_IOP) {
                                            // oc_iop
                                            // Get inventory object right-click option
											isp -= 2;
											tempInt2 = scriptIntValues[isp]; // Object type ID
											tempInt1 = scriptIntValues[isp + 1]; // Option slot
											invObjType = ObjTypeList.get(tempInt2);
											if (tempInt1 >= 1 && tempInt1 <= 5 && invObjType.iop[tempInt1 - 1] != null) {
												scriptStringValues[ssp++] = invObjType.iop[tempInt1 - 1];
												continue;
											}
											scriptStringValues[ssp++] = EMPTY_STRING; // No option in that slot
											continue;
										}
										if (opcode == OC_COST) {
                                            // oc_cost
                                            // Get object shop value
											isp--; // Decrement then read
											tempInt2 = scriptIntValues[isp];
											scriptIntValues[isp++] = ObjTypeList.get(tempInt2).cost;
											continue;
										}
										if (opcode == OC_STACKABLE) {
                                            // oc_stackable
                                            // Check if object is stackable
											isp--; // Decrement then read
											tempInt2 = scriptIntValues[isp];
											scriptIntValues[isp++] = ObjTypeList.get(tempInt2).stackable == 1 ? 1 : 0;
											continue;
										}
										@Pc(11417) ObjType targetObjType;
										if (opcode == OC_CERT) {
                                            // oc_cert
                                            // Get certificate link
                                            // Returns the actual object if this is a certificate
											isp--; // Decrement then read
											tempInt2 = scriptIntValues[isp];
											targetObjType = ObjTypeList.get(tempInt2);
											if (targetObjType.certTemplate == -1 && targetObjType.certLink >= 0) {
												scriptIntValues[isp++] = targetObjType.certLink; // This IS a cert, return actual object
												continue;
											}
											scriptIntValues[isp++] = tempInt2;  // Not a cert, return original ID
											continue;
										}
										if (opcode == OC_UNCERT) {
                                            // oc_uncert
                                            // Get uncertificate link
                                            // // Returns the certificate version of this object
											isp--; // Decrement then read
											tempInt2 = scriptIntValues[isp];
											targetObjType = ObjTypeList.get(tempInt2);
											if (targetObjType.certTemplate >= 0 && targetObjType.certLink >= 0) {
												scriptIntValues[isp++] = targetObjType.certLink; // Return certificate version
												continue;
											}
											scriptIntValues[isp++] = tempInt2; // No certificate exists, return original
											continue;
										}
										if (opcode == OC_MEMBERS) {
											isp--; // Decrement then read
											tempInt2 = scriptIntValues[isp];
											scriptIntValues[isp++] = ObjTypeList.get(tempInt2).members ? 1 : 0;
											continue;
										}
										if (opcode == OC_PARAM) {
											isp -= 2;
											tempInt2 = scriptIntValues[isp];
											tempInt1 = scriptIntValues[isp + 1];
											paramType = ParamTypeList.get(tempInt1);
											if (paramType.isString()) {
												scriptStringValues[ssp++] = ObjTypeList.get(tempInt2).getParam(paramType.defaultString, tempInt1);
											} else {
												scriptIntValues[isp++] = ObjTypeList.get(tempInt2).getParam(paramType.defaultInt, tempInt1);
											}
											continue;
										}
										if (opcode == OC_FIND) {
                                            // oc_find
                                            // Search for objects by name
											ssp--;
											tempString1 = scriptStringValues[ssp]; // Search string
											isp--; // Decrement then read
											tempInt1 = scriptIntValues[isp]; // Search type (0=exact, 1=contains)
											Find.search(tempInt1 == 1, tempString1); // Populate Find.results
											scriptIntValues[isp++] = Find.index; // Return result count
											continue;
										}
										if (opcode == OC_FINDNEXT) {
                                            // oc_findnext
                                            // Get next object ID from search results
											if (Find.results != null && Find.size < Find.index) {
												scriptIntValues[isp++] = Find.results[Find.size++] & 0xFFFF;
												continue;
											}
											scriptIntValues[isp++] = -1; // No more results
											continue;
										}
										if (opcode == OC_FINDRESET) {
                                            // oc_findreset
                                            // Reset search iterator
											Find.size = 0;
											continue;
										}
									} else if (opcode < 4400) {
										if (opcode == NC_PARAM) {
											isp -= 2;
											tempInt2 = scriptIntValues[isp];
											tempInt1 = scriptIntValues[isp + 1];
											paramType = ParamTypeList.get(tempInt1);
											if (paramType.isString()) {
												scriptStringValues[ssp++] = NpcTypeList.get(tempInt2).getParam(tempInt1, paramType.defaultString);
											} else {
												scriptIntValues[isp++] = NpcTypeList.get(tempInt2).getParam(tempInt1, paramType.defaultInt);
											}
											continue;
										}
									} else if (opcode >= 4500) {
										if (opcode >= 4600) {
											if (opcode < 5100) {
												if (opcode == CHAT_GETFILTER_PUBLIC) {
													scriptIntValues[isp++] = Chat.publicFilter;
													continue;
												}
												if (opcode == CHAT_SETFILTER) {
													isp -= 3;
													Chat.publicFilter = scriptIntValues[isp];
													Chat.privateFilter = scriptIntValues[isp + 1];
													Chat.tradeFilter = scriptIntValues[isp + 2];
													Protocol.outboundBuffer.pIsaac1(ClientProt.CLIENTSCRIPT_TRIGGERED);
													Protocol.outboundBuffer.p1(Chat.publicFilter);
													Protocol.outboundBuffer.p1(Chat.privateFilter);
													Protocol.outboundBuffer.p1(Chat.tradeFilter);
													continue;
												}
												if (opcode == REPORTABUSE) {
													ssp--;
													tempString1 = scriptStringValues[ssp];
													isp -= 2;
													tempInt1 = scriptIntValues[isp];
													j = scriptIntValues[isp + 1];
													Protocol.outboundBuffer.pIsaac1(REPORT_ABUSE);
													Protocol.outboundBuffer.p8(tempString1.encode37());
													Protocol.outboundBuffer.p1(tempInt1 - 1);
													Protocol.outboundBuffer.p1(j);
													continue;
												}
												if (opcode == CHAT_GETHISTORY_BYTYPEANDLINE) {
													chatTypedLowercase = null;
													isp--; // Decrement then read
													tempInt2 = scriptIntValues[isp];
													if (tempInt2 < 100) {
														chatTypedLowercase = Chat.messages[tempInt2];
													}
													if (chatTypedLowercase == null) {
														chatTypedLowercase = EMPTY_STRING;
													}
													scriptStringValues[ssp++] = chatTypedLowercase;
													continue;
												}
												if (opcode == CHAT_GETHISTORY_BYTYPE) {
													isp--; // Decrement then read
													tempInt2 = scriptIntValues[isp];
													tempInt1 = -1;
													if (tempInt2 < 100 && Chat.messages[tempInt2] != null) {
														tempInt1 = Chat.types[tempInt2];
													}
													scriptIntValues[isp++] = tempInt1;
													continue;
												}
												if (opcode == CHAT_GETFILTER_PRIVATE) {
													scriptIntValues[isp++] = Chat.privateFilter;
													continue;
												}
												if (opcode == CHAT_SENDPUBLIC) {
													ssp--;
													tempString1 = scriptStringValues[ssp];
													if (!tempString1.startsWith(SCOPE_SEPARATOR)) {
														if (LoginManager.staffModLevel == 0 && (LoginManager.playerUnderage && !LoginManager.parentalChatConsent || LoginManager.worldQuickChat)) {
															continue;
														}
														chatTypedLowercase = tempString1.toLowerCase();
														@Pc(5555) byte color = 0;
														if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL0)) {
															color = 0;
															tempString1 = tempString1.substring(LocalizedText.STABLE_CHATCOL0.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL1)) {
															tempString1 = tempString1.substring(LocalizedText.STABLE_CHATCOL1.length());
															color = 1;
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL2)) {
															tempString1 = tempString1.substring(LocalizedText.STABLE_CHATCOL2.length());
															color = 2;
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL3)) {
															color = 3;
															tempString1 = tempString1.substring(LocalizedText.STABLE_CHATCOL3.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL4)) {
															tempString1 = tempString1.substring(LocalizedText.STABLE_CHATCOL4.length());
															color = 4;
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL5)) {
															tempString1 = tempString1.substring(LocalizedText.STABLE_CHATCOL5.length());
															color = 5;
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL6)) {
															color = 6;
															tempString1 = tempString1.substring(LocalizedText.STABLE_CHATCOL6.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL7)) {
															color = 7;
															tempString1 = tempString1.substring(LocalizedText.STABLE_CHATCOL7.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL8)) {
															tempString1 = tempString1.substring(LocalizedText.STABLE_CHATCOL8.length());
															color = 8;
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL9)) {
															color = 9;
															tempString1 = tempString1.substring(LocalizedText.STABLE_CHATCOL9.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL10)) {
															color = 10;
															tempString1 = tempString1.substring(LocalizedText.STABLE_CHATCOL10.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL11)) {
															tempString1 = tempString1.substring(LocalizedText.STABLE_CHATCOL11.length());
															color = 11;
														} else if (Client.language != 0) {
															if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL0)) {
																color = 0;
																tempString1 = tempString1.substring(LocalizedText.CHATCOL0.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL1)) {
																tempString1 = tempString1.substring(LocalizedText.CHATCOL1.length());
																color = 1;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL2)) {
																tempString1 = tempString1.substring(LocalizedText.CHATCOL2.length());
																color = 2;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL3)) {
																tempString1 = tempString1.substring(LocalizedText.CHATCOL3.length());
																color = 3;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL4)) {
																tempString1 = tempString1.substring(LocalizedText.CHATCOL4.length());
																color = 4;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL5)) {
																color = 5;
																tempString1 = tempString1.substring(LocalizedText.CHATCOL5.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL6)) {
																tempString1 = tempString1.substring(LocalizedText.CHATCOL6.length());
																color = 6;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL7)) {
																color = 7;
																tempString1 = tempString1.substring(LocalizedText.CHATCOL7.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL8)) {
																color = 8;
																tempString1 = tempString1.substring(LocalizedText.CHATCOL8.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL9)) {
																color = 9;
																tempString1 = tempString1.substring(LocalizedText.CHATCOL9.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL10)) {
																tempString1 = tempString1.substring(LocalizedText.CHATCOL10.length());
																color = 10;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL11)) {
																tempString1 = tempString1.substring(LocalizedText.CHATCOL11.length());
																color = 11;
															}
														}

                                                        // Parse chat effects from message prefix
														@Pc(5943) byte effect = 0; // 0=none, 1=wave, 2=wave2, 3=shake, 4=scroll, 5=slide
														chatTypedLowercase = tempString1.toLowerCase();
														if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATEFFECT1)) {
															tempString1 = tempString1.substring(LocalizedText.STABLE_CHATEFFECT1.length());  // Remove prefix
															effect = 1; // Wave effect
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATEFFECT2)) {
															effect = 2; // Wave2 effect
															tempString1 = tempString1.substring(LocalizedText.STABLE_CHATEFFECT2.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATEFFECT3)) {
															tempString1 = tempString1.substring(LocalizedText.STABLE_CHATEFFECT3.length());
															effect = 3; // Shake effect
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATEFFECT4)) {
															effect = 4; // Scroll effect
															tempString1 = tempString1.substring(LocalizedText.STABLE_CHATEFFECT4.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATEFFECTC5)) {
															effect = 5; // Slide effect
															tempString1 = tempString1.substring(LocalizedText.STABLE_CHATEFFECTC5.length());
														} else if (Client.language != 0) {
															if (chatTypedLowercase.startsWith(LocalizedText.CHATEFFECT1)) {
																tempString1 = tempString1.substring(LocalizedText.CHATEFFECT1.length());
																effect = 1;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATEFFECT2)) {
																effect = 2;
																tempString1 = tempString1.substring(LocalizedText.CHATEFFECT2.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATEFFECT3)) {
																effect = 3;
																tempString1 = tempString1.substring(LocalizedText.CHATEFFECT3.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATEFFECT4)) {
																effect = 4;
																tempString1 = tempString1.substring(LocalizedText.CHATEFFECT4.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATEFFECT5)) {
																tempString1 = tempString1.substring(LocalizedText.CHATEFFECT5.length());
																effect = 5;
															}
														}
														Protocol.outboundBuffer.pIsaac1(MESSAGE_PUBLIC);
														Protocol.outboundBuffer.p1(0);
														start = Protocol.outboundBuffer.offset;
														Protocol.outboundBuffer.p1(color);
														Protocol.outboundBuffer.p1(effect);
														WordPack.writeString(Protocol.outboundBuffer, tempString1);
														Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - start);
														continue;
													}
													Cheat.execute(tempString1);
													continue;
												}
												if (opcode == CHAT_SENDPRIVATE) {
													ssp -= 2;
													chatTypedLowercase = scriptStringValues[ssp + 1];
													tempString1 = scriptStringValues[ssp];
													if (LoginManager.staffModLevel != 0 || (!LoginManager.playerUnderage || LoginManager.parentalChatConsent) && !LoginManager.worldQuickChat) {
														Protocol.outboundBuffer.pIsaac1(MESSAGE_PRIVATE);
														Protocol.outboundBuffer.p1(0);
														j = Protocol.outboundBuffer.offset;
														Protocol.outboundBuffer.p8(tempString1.encode37());
														WordPack.writeString(Protocol.outboundBuffer, chatTypedLowercase);
														Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - j);
													}
													continue;
												}
												if (opcode == CHAT_GETHISTORY_USERNAME) {
													isp--; // Decrement then read
													tempInt2 = scriptIntValues[isp];
													chatTypedLowercase = null;
													if (tempInt2 < 100) {
														chatTypedLowercase = Chat.names[tempInt2];
													}
													if (chatTypedLowercase == null) {
														chatTypedLowercase = EMPTY_STRING;
													}
													scriptStringValues[ssp++] = chatTypedLowercase;
													continue;
												}
												if (opcode == CHAT_GETHISTORY_CLAN) {
													isp--; // Decrement then read
													tempInt2 = scriptIntValues[isp];
													chatTypedLowercase = null;
													if (tempInt2 < 100) {
														chatTypedLowercase = Chat.clans[tempInt2];
													}
													if (chatTypedLowercase == null) {
														chatTypedLowercase = EMPTY_STRING;
													}
													scriptStringValues[ssp++] = chatTypedLowercase;
													continue;
												}
												if (opcode == CHAT_GETHISTORY_QUICKCHATID) {
													isp--; // Decrement then read
													tempInt2 = scriptIntValues[isp];
													tempInt1 = -1;
													if (tempInt2 < 100) {
														tempInt1 = Chat.phraseIds[tempInt2];
													}
													scriptIntValues[isp++] = tempInt1;
													continue;
												}
												if (opcode == GETPLAYERNAME) {
													if (PlayerList.self == null || PlayerList.self.username == null) {
														tempString1 = Player.usernameInput;
													} else {
														tempString1 = PlayerList.self.getUsername();
													}
													scriptStringValues[ssp++] = tempString1;
													continue;
												}
												if (opcode == CHAT_GETFILTER_TRADE) {
													scriptIntValues[isp++] = Chat.tradeFilter;
													continue;
												}
												if (opcode == CHAT_GETHISTORYLENGTH) {
													scriptIntValues[isp++] = Chat.size;
													continue;
												}
												if (opcode == QUICKCHAT_GETCATEGORYNAME) {
													isp--; // Decrement then read
													tempInt2 = scriptIntValues[isp];
													scriptStringValues[ssp++] = QuickChatCatTypeList.get(tempInt2).description;
													continue;
												}
												@Pc(6378) QuickChatCatType quickChatCategory;
												if (opcode == QUICKCHAT_GETCATEGORYSIZE) {
													isp--; // Decrement then read
													tempInt2 = scriptIntValues[isp];
													quickChatCategory = QuickChatCatTypeList.get(tempInt2);
													if (quickChatCategory.subcategories == null) {
														scriptIntValues[isp++] = 0;
													} else {
														scriptIntValues[isp++] = quickChatCategory.subcategories.length;
													}
													continue;
												}
												if (opcode == QUICKCHAT_GETSUBCATEGORY) {
													isp -= 2;
													tempInt2 = scriptIntValues[isp];
													tempInt1 = scriptIntValues[isp + 1];
													@Pc(6416) QuickChatCatType quickChatSubcategory = QuickChatCatTypeList.get(tempInt2);
													i = quickChatSubcategory.subcategories[tempInt1];
													scriptIntValues[isp++] = i;
													continue;
												}
												if (opcode == QUICKCHAT_GETCATEGORYPHRASESIZE) {
													isp--; // Decrement then read
													tempInt2 = scriptIntValues[isp];
													quickChatCategory = QuickChatCatTypeList.get(tempInt2);
													if (quickChatCategory.phrases == null) {
														scriptIntValues[isp++] = 0;
													} else {
														scriptIntValues[isp++] = quickChatCategory.phrases.length;
													}
													continue;
												}
												if (opcode == QUICKCHAT_GETCATEGORYPHRASE) {
													isp -= 2;
													tempInt1 = scriptIntValues[isp + 1];
													tempInt2 = scriptIntValues[isp];
													scriptIntValues[isp++] = QuickChatCatTypeList.get(tempInt2).phrases[tempInt1];
													continue;
												}
												if (opcode == QUICKCHAT_GETPHRASETRING) {
													isp--; // Decrement then read
													tempInt2 = scriptIntValues[isp];
													scriptStringValues[ssp++] = QuickChatPhraseTypeList.get(tempInt2).getText();
													continue;
												}
												if (opcode == QUICKCHAT_GETPHRASEDYNAMICCOMMANDCOUNT) {
													isp--; // Decrement then read
													tempInt2 = scriptIntValues[isp];
													@Pc(6527) QuickChatPhraseType quickChatPhrase = QuickChatPhraseTypeList.get(tempInt2);
													if (quickChatPhrase.autoResponses == null) {
														scriptIntValues[isp++] = 0;
													} else {
														scriptIntValues[isp++] = quickChatPhrase.autoResponses.length;
													}
													continue;
												}
												if (opcode == QUICKCHAT_GETPHRASEDYNAMICCOMMAND) {
													isp -= 2;
													tempInt1 = scriptIntValues[isp + 1];
													tempInt2 = scriptIntValues[isp];
													scriptIntValues[isp++] = QuickChatPhraseTypeList.get(tempInt2).autoResponses[tempInt1];
													continue;
												}
												if (opcode == QUICKCHAT_INIT) {
													activePhrase = new QuickChatPhrase();
													isp--; // Decrement then read
													activePhrase.id = scriptIntValues[isp];
													activePhrase.type = QuickChatPhraseTypeList.get(activePhrase.id);
													activePhrase.values = new int[activePhrase.type.getDynamicCommandCount()];
													continue;
												}
												if (opcode == QUICKCHAT_SENDPUBLIC) {
													Protocol.outboundBuffer.pIsaac1(ClientProt.CLIENTSCRIPT_167);
													Protocol.outboundBuffer.p1(0);
													tempInt2 = Protocol.outboundBuffer.offset;
													Protocol.outboundBuffer.p1(0);
													Protocol.outboundBuffer.p2(activePhrase.id);
													activePhrase.type.encodeMessage(Protocol.outboundBuffer, activePhrase.values);
													Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - tempInt2);
													continue;
												}
												if (opcode == QUICKCHAT_SENDPRIVATE) {
													ssp--;
													tempString1 = scriptStringValues[ssp];
													Protocol.outboundBuffer.pIsaac1(ClientProt.CLIENTSCRIPT_178);
													Protocol.outboundBuffer.p1(0);
													tempInt1 = Protocol.outboundBuffer.offset;
													Protocol.outboundBuffer.p8(tempString1.encode37());
													Protocol.outboundBuffer.p2(activePhrase.id);
													activePhrase.type.encodeMessage(Protocol.outboundBuffer, activePhrase.values);
													Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - tempInt1);
													continue;
												}
												if (opcode == QUICKCHAT_SENDCLAN) {
													Protocol.outboundBuffer.pIsaac1(ClientProt.CLIENTSCRIPT_167);
													Protocol.outboundBuffer.p1(0);
													tempInt2 = Protocol.outboundBuffer.offset;
													Protocol.outboundBuffer.p1(1);
													Protocol.outboundBuffer.p2(activePhrase.id);
													activePhrase.type.encodeMessage(Protocol.outboundBuffer, activePhrase.values);
													Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - tempInt2);
													continue;
												}
												if (opcode == QUICKCHAT_GETSUBCATEGORYSHORTCUT) {
													isp -= 2;
													tempInt1 = scriptIntValues[isp + 1];
													tempInt2 = scriptIntValues[isp];
													scriptIntValues[isp++] = QuickChatCatTypeList.get(tempInt2).subcategoryShortcuts[tempInt1];
													continue;
												}
												if (opcode == QUICKCHAT_GETPHRASESHORTCUT) {
													isp -= 2;
													tempInt1 = scriptIntValues[isp + 1];
													tempInt2 = scriptIntValues[isp];
													scriptIntValues[isp++] = QuickChatCatTypeList.get(tempInt2).phraseShortcuts[tempInt1];
													continue;
												}
												if (opcode == QUICKCHAT_GETSUBCATEGORYINDEX) {
													isp -= 2;
													tempInt1 = scriptIntValues[isp + 1];
													tempInt2 = scriptIntValues[isp];
													if (tempInt1 == -1) {
														scriptIntValues[isp++] = -1;
													} else {
														scriptIntValues[isp++] = QuickChatCatTypeList.get(tempInt2).method469(tempInt1);
													}
													continue;
												}
												if (opcode == QUICKCHAT_GETPHRASEINDEX) {
													isp -= 2;
													tempInt2 = scriptIntValues[isp];
													tempInt1 = scriptIntValues[isp + 1];
													if (tempInt1 == -1) {
														scriptIntValues[isp++] = -1;
													} else {
														scriptIntValues[isp++] = QuickChatCatTypeList.get(tempInt2).method466(tempInt1);
													}
													continue;
												}
												if (opcode == QUICKCHAT_GETPHRASEDYNAMICCOUNT) {
													isp--; // Decrement then read
													tempInt2 = scriptIntValues[isp];
													scriptIntValues[isp++] = QuickChatPhraseTypeList.get(tempInt2).getDynamicCommandCount();
													continue;
												}
												if (opcode == QUICKCHAT_GETPHRASEDYNAMIC) {
													isp -= 2;
													tempInt1 = scriptIntValues[isp + 1];
													tempInt2 = scriptIntValues[isp];
													j = QuickChatPhraseTypeList.get(tempInt2).getDynamicCommand(tempInt1);
													scriptIntValues[isp++] = j;
													continue;
												}
												if (opcode == QUICKCHAT_SETDYNAMIC) {
													isp -= 2;
													tempInt2 = scriptIntValues[isp];
													tempInt1 = scriptIntValues[isp + 1];
													activePhrase.values[tempInt2] = tempInt1;
													continue;
												}
												if (opcode == QUICKCHAT_SETDYNAMIC_ALT) {
													isp -= 2;
													tempInt2 = scriptIntValues[isp];
													tempInt1 = scriptIntValues[isp + 1];
													activePhrase.values[tempInt2] = tempInt1;
													continue;
												}
												if (opcode == QUICKCHAT_GETPHRASEPARAM) {
													isp -= 3;
													tempInt2 = scriptIntValues[isp];
													j = scriptIntValues[isp + 2];
													tempInt1 = scriptIntValues[isp + 1];
													@Pc(6996) QuickChatPhraseType autoResponsePhrase = QuickChatPhraseTypeList.get(tempInt2);
													if (autoResponsePhrase.getDynamicCommand(tempInt1) != 0) {
														throw new RuntimeException("bad command");
													}
													scriptIntValues[isp++] = autoResponsePhrase.getDynamicCommandParam(j, tempInt1);
													continue;
												}
												if (opcode == QUICKCHAT_FINDPHRASE) {
													ssp--;
													tempString1 = scriptStringValues[ssp];
													isp--; // Decrement then read
													local1552 = scriptIntValues[isp] == 1;
													Find.findQuickChatPhrases(local1552, tempString1);
													scriptIntValues[isp++] = Find.index;
													continue;
												}
												if (opcode == QUICKCHAT_FINDNEXT) {
													if (Find.results != null && Find.size < Find.index) {
														scriptIntValues[isp++] = Find.results[Find.size++] & 0xFFFF;
														continue;
													}
													scriptIntValues[isp++] = -1;
													continue;
												}
												if (opcode == QUICKCHAT_FINDRESET) {
													Find.size = 0;
													continue;
												}
											} else if (opcode < 5200) {
												if (opcode == KEY_HELD_V) {
													if (Keyboard.pressedKeys[86]) {
														scriptIntValues[isp++] = 1;
													} else {
														scriptIntValues[isp++] = 0;
													}
													continue;
												}
												if (opcode == KEY_HELD_R) {
													if (Keyboard.pressedKeys[82]) {
														scriptIntValues[isp++] = 1;
													} else {
														scriptIntValues[isp++] = 0;
													}
													continue;
												}
												if (opcode == KEY_HELD_Q) {
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
													if (opcode == WORLDMAP_SETZOOM) {
														isp--; // Decrement then read
														WorldMap.setTargetZoom(scriptIntValues[isp]);
														continue;
													}
													if (opcode == WORLDMAP_GETZOOM) {
														scriptIntValues[isp++] = WorldMap.getTargetZoom();
														continue;
													}
													if (opcode == WORLDMAP_METHOD4444) {
														isp--; // Decrement then read
														WorldMap.method4444(scriptIntValues[isp]);
														continue;
													}
													if (opcode == WORLDMAP_METHOD4656) {
														ssp--;
														WorldMap.method4656(scriptStringValues[ssp]);
														continue;
													}
													if (opcode == WORLDMAP_METHOD923) {
														scriptStringValues[ssp - 1] = WorldMap.method923(scriptStringValues[ssp - 1]);
														continue;
													}
													if (opcode == WORLDMAP_METHOD1853) {
														ssp--;
														WorldMap.method1853(scriptStringValues[ssp]);
														continue;
													}
													if (opcode == WORLDMAP_GETMAPNAME) {
														isp--; // Decrement then read
														tempInt2 = scriptIntValues[isp];
														@Pc(7264) Map sourceMap = MapList.getContainingSource(tempInt2 >> 14 & 0x3FFF, tempInt2 & 0x3FFF);
														if (sourceMap == null) {
															scriptStringValues[ssp++] = EMPTY_STRING;
														} else {
															scriptStringValues[ssp++] = sourceMap.group;
														}
														continue;
													}
													@Pc(7293) Map worldMap;
													if (opcode == WORLDMAP_GETDISPLAYNAME) {
														ssp--;
														worldMap = MapList.get(scriptStringValues[ssp]);
														if (worldMap != null && worldMap.name != null) {
															scriptStringValues[ssp++] = worldMap.name;
															continue;
														}
														scriptStringValues[ssp++] = EMPTY_STRING;
														continue;
													}
													if (opcode == WORLDMAP_GETCURRENTPOS) {
														scriptIntValues[isp++] = WorldMap.anInt2387;
														scriptIntValues[isp++] = WorldMap.anInt1176;
														continue;
													}
													if (opcode == WORLDMAP_GETDISPLAYBOUNDS) {
														scriptIntValues[isp++] = WorldMap.originX + WorldMap.anInt435;
														scriptIntValues[isp++] = WorldMap.originZ + WorldMap.length - WorldMap.anInt919 - 1;
														continue;
													}
													if (opcode == WORLDMAP_GETCURRENTMAPORIGIN) {
														worldMap = WorldMap.getCurrentMap();
														if (worldMap == null) {
															scriptIntValues[isp++] = 0;
															scriptIntValues[isp++] = 0;
														} else {
															scriptIntValues[isp++] = worldMap.originX * 64;
															scriptIntValues[isp++] = worldMap.originZ * 64;
														}
														continue;
													}
													if (opcode == WORLDMAP_GETCURRENTMAPSIZE) {
														worldMap = WorldMap.getCurrentMap();
														if (worldMap == null) {
															scriptIntValues[isp++] = 0;
															scriptIntValues[isp++] = 0;
														} else {
															scriptIntValues[isp++] = worldMap.displayMaxZ - worldMap.displayMinX;
															scriptIntValues[isp++] = worldMap.displayMinZ - worldMap.displayMaxX;
														}
														continue;
													}
													if (opcode == WORLDMAP_GETHOVEREDLABEL) {
														tempInt2 = WorldMap.method2352();
														j = 0;
														if (tempInt2 == -1) {
															chatTypedLowercase = EMPTY_STRING;
														} else {
															chatTypedLowercase = WorldMap.labels.text[tempInt2];
															j = WorldMap.labels.getLowerTwoBits(tempInt2);
														}
														chatTypedLowercase = chatTypedLowercase.method3140(SPACE_STRING, HTML_LINE_BREAK);
														scriptStringValues[ssp++] = chatTypedLowercase;
														scriptIntValues[isp++] = j;
														continue;
													}
													if (opcode == WORLDMAP_GETCLICKEDLABEL) {
														j = 0;
														tempInt2 = WorldMap.method2385();
														if (tempInt2 == -1) {
															chatTypedLowercase = EMPTY_STRING;
														} else {
															chatTypedLowercase = WorldMap.labels.text[tempInt2];
															j = WorldMap.labels.getLowerTwoBits(tempInt2);
														}
														chatTypedLowercase = chatTypedLowercase.method3140(SPACE_STRING, HTML_LINE_BREAK);
														scriptStringValues[ssp++] = chatTypedLowercase;
														scriptIntValues[isp++] = j;
														continue;
													}
													if (opcode == WORLDMAP_JUMPTODISPLAYCOORD) {
														isp--; // Decrement then read
														tempInt2 = scriptIntValues[isp];
														WorldMap.method3616(tempInt2 >> 14 & 0x3FFF, tempInt2 & 0x3FFF);
														continue;
													}
													if (opcode == WORLDMAP_COORDHASMAP) {
														isp--; // Decrement then read
														tempInt2 = scriptIntValues[isp];
														ssp--;
														chatTypedLowercase = scriptStringValues[ssp];
														local7566 = false;
														@Pc(7577) SecondaryLinkedList mapEntries = method3333(tempInt2 >> 14 & 0x3FFF, tempInt2 & 0x3FFF);
														for (@Pc(7582) Map currentMap = (Map) mapEntries.head(); currentMap != null; currentMap = (Map) mapEntries.next()) {
															if (currentMap.group.equalsIgnoreCase(chatTypedLowercase)) {
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
													if (opcode == WORLDMAP_FLASHELEMENT) {
														isp--; // Decrement then read
														tempInt2 = scriptIntValues[isp];
														MapList.method4332(tempInt2);
														continue;
													}
													if (opcode == WORLDMAP_ISELEMENTFLASHING) {
														isp--; // Decrement then read
														tempInt2 = scriptIntValues[isp];
														if (MapList.method1855(tempInt2)) {
															scriptIntValues[isp++] = 1;
														} else {
															scriptIntValues[isp++] = 0;
														}
														continue;
													}
													if (opcode == WORLDMAP_GETCURRENTMAPDEFAULTZOOM) {
														worldMap = WorldMap.getCurrentMap();
														if (worldMap == null) {
															scriptIntValues[isp++] = -1;
														} else {
															scriptIntValues[isp++] = worldMap.defaultZoom;
														}
														continue;
													}
													if (opcode == WORLDMAP_METHOD1149) {
														ssp--;
														WorldMap.method1149(scriptStringValues[ssp]);
														continue;
													}
													if (opcode == WORLDMAP_ISLOADED) {
														scriptIntValues[isp++] = WorldMap.loadPercentage == 100 ? 1 : 0;
														continue;
													}
												} else if (opcode < 5400) {
													if (opcode == SETFULLSCREEN) {
														isp -= 2;
														tempInt1 = scriptIntValues[isp + 1];
														tempInt2 = scriptIntValues[isp];
														DisplayMode.setWindowMode(false, 3, tempInt2, tempInt1);
														scriptIntValues[isp++] = GameShell.fullScreenFrame == null ? 0 : 1;
														continue;
													}
													if (opcode == EXITFULLSCREEN) {
														if (GameShell.fullScreenFrame != null) {
															DisplayMode.setWindowMode(false, Preferences.favoriteWorlds, -1, -1);
														}
														continue;
													}
													if (opcode == GETDISPLAYMODECOUNT) {
														@Pc(7780) DisplayMode[] availableModes = DisplayMode.getModes();
														scriptIntValues[isp++] = availableModes.length;
														continue;
													}
													if (opcode == GETDISPLAYMODESIZE) {
														isp--; // Decrement then read
														tempInt2 = scriptIntValues[isp];
														@Pc(7800) DisplayMode[] availableModes = DisplayMode.getModes();
														scriptIntValues[isp++] = availableModes[tempInt2].width;
														scriptIntValues[isp++] = availableModes[tempInt2].height;
														continue;
													}
													if (opcode == GETDISPLAYMODEINDEX) {
														tempInt1 = Preferences.fullScreenHeight;
														tempInt2 = Preferences.fullScreenWidth;
														j = -1;
														@Pc(7833) DisplayMode[] displayModes = DisplayMode.getModes();
														for (start = 0; start < displayModes.length; start++) {
															@Pc(7843) DisplayMode currentMode = displayModes[start];
															if (tempInt2 == currentMode.width && currentMode.height == tempInt1) {
																j = start;
																break;
															}
														}
														scriptIntValues[isp++] = j;
														continue;
													}
													if (opcode == GETWINDOWMODE) {
														scriptIntValues[isp++] = DisplayMode.getWindowMode();
														continue;
													}
													if (opcode == SETWINDOWMODE) {
														isp--; // Decrement then read
														tempInt2 = scriptIntValues[isp];
														if (tempInt2 < 0 || tempInt2 > 2) {
															tempInt2 = 0;
														}
														DisplayMode.setWindowMode(false, tempInt2, -1, -1);
														continue;
													}
													if (opcode == GETDEFAULTWINDOWMODE) {
														scriptIntValues[isp++] = Preferences.favoriteWorlds;
														continue;
													}
													if (opcode == SETDEFAULTWINDOWMODE) {
														isp--; // Decrement then read
														tempInt2 = scriptIntValues[isp];
														if (tempInt2 < 0 || tempInt2 > 2) {
															tempInt2 = 0;
														}
														Preferences.favoriteWorlds = tempInt2;
														Preferences.write(GameShell.signLink);
														continue;
													}
												} else if (opcode < 5500) {
													if (opcode == CLIENTSCRIPT_117) {
														ssp -= 2;
														tempString1 = scriptStringValues[ssp];
														chatTypedLowercase = scriptStringValues[ssp + 1];
														isp--; // Decrement then read
														j = scriptIntValues[isp];
														Protocol.outboundBuffer.pIsaac1(ClientProt.CLIENTSCRIPT_117);
														Protocol.outboundBuffer.p1(Packet.gjstrlen(tempString1) + Packet.gjstrlen(chatTypedLowercase) + 1);
														Protocol.outboundBuffer.pjstr(tempString1);
														Protocol.outboundBuffer.pjstr(chatTypedLowercase);
														Protocol.outboundBuffer.p1(j);
														continue;
													}
													if (opcode == SETCOLOROVERRIDE) {
														isp -= 2;
														Client.aShortArray88[scriptIntValues[isp]] = (short) ColorUtils.rgbToHsl(scriptIntValues[isp + 1]);
														ObjTypeList.clearModels();
														ObjTypeList.clearSprites();
														NpcTypeList.method4649();
														NpcTypeList.method443();
														method1807();
														continue;
													}
													if (opcode == CAM_SPLINE_INIT) {
														isp -= 2;
														tempInt2 = scriptIntValues[isp];
														tempInt1 = scriptIntValues[isp + 1];
														if (tempInt2 >= 0 && tempInt2 < 2) {
															Camera.cameraSplines[tempInt2] = new int[tempInt1 << 1][4];
														}
														continue;
													}
													if (opcode == CAM_SPLINE_ADDPOINT) {
														isp -= 7;
														tempInt2 = scriptIntValues[isp];
														tempInt1 = scriptIntValues[isp + 1] << 1;
														i = scriptIntValues[isp + 3];
														j = scriptIntValues[isp + 2];
														start = scriptIntValues[isp + 4];
														@Pc(8108) int splineRotation = scriptIntValues[isp + 6];
														childId = scriptIntValues[isp + 5];
														if (tempInt2 >= 0 && tempInt2 < 2 && Camera.cameraSplines[tempInt2] != null && tempInt1 >= 0 && Camera.cameraSplines[tempInt2].length > tempInt1) {
															Camera.cameraSplines[tempInt2][tempInt1] = new int[] { (j >> 14 & 0x3FFF) * 128, i, (j & 0x3FFF) * 128, splineRotation };
															Camera.cameraSplines[tempInt2][tempInt1 + 1] = new int[] { (start >> 14 & 0x3FFF) * 128, childId, (start & 0x3FFF) * 128 };
														}
														continue;
													}
													if (opcode == CAM_SPLINE_LENGTH) {
														isp--; // Decrement then read
														tempInt2 = Camera.cameraSplines[scriptIntValues[isp]].length >> 1;
														scriptIntValues[isp++] = tempInt2;
														continue;
													}
													if (opcode == QUIT_TO_SETTINGS) {
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
													if (opcode == GETLASTLOGINADDRESS) {
														tempString1 = EMPTY_STRING;
														if (Player.lastLogAddress != null) {
															tempString1 = JString.formatIp(Player.lastLogAddress.intArg2);
															try {
																if (Player.lastLogAddress.result != null) {
																	@Pc(8281) byte[] ipBytes = ((String) Player.lastLogAddress.result).getBytes("ISO-8859-1");
																	tempString1 = JString.decodeString(ipBytes, ipBytes.length, 0);
																}
															} catch (@Pc(8290) UnsupportedEncodingException ignored) {
															}
														}
														scriptStringValues[ssp++] = tempString1;
														continue;
													}
													if (opcode == ISAPPLET) {
														scriptIntValues[isp++] = SignLink.anInt5928 == 3 ? 1 : 0;
														continue;
													}
													if (opcode == OPENURL) {
														if (GameShell.fullScreenFrame != null) {
															DisplayMode.setWindowMode(false, Preferences.favoriteWorlds, -1, -1);
														}
														isp--; // Decrement then read
														local1552 = scriptIntValues[isp] == 1;
														ssp--;
														tempString1 = scriptStringValues[ssp];
														@Pc(8356) JString fullUrl = JString.concatenate(new JString[] { buildConfigUrl(), tempString1 });
														if (GameShell.frame != null || local1552 && SignLink.anInt5928 != 3 && SignLink.osName.startsWith("win") && !Client.haveIe6) {
															Protocol.newTab = local1552;
															url = fullUrl;
															Protocol.openUrlRequest = GameShell.signLink.openUrl(new String(fullUrl.method3148(), "ISO-8859-1"));
															continue;
														}
														openUrl(fullUrl, local1552);
														continue;
													}
													if (opcode == SETPLAYERNAME) {
														isp--; // Decrement then read
														j = scriptIntValues[isp];
														ssp -= 2;
														chatTypedLowercase = scriptStringValues[ssp + 1];
														tempString1 = scriptStringValues[ssp];
														if (tempString1.length() > 0) {
															if (PlayerList.playerNames == null) {
																PlayerList.playerNames = new JString[PlayerList.anIntArray309[Client.game]];
															}
															PlayerList.playerNames[j] = tempString1;
														}
														if (chatTypedLowercase.length() > 0) {
															if (PlayerList.playerNames2 == null) {
																PlayerList.playerNames2 = new JString[PlayerList.anIntArray309[Client.game]];
															}
															PlayerList.playerNames2[j] = chatTypedLowercase;
														}
														continue;
													}
													if (opcode == PRINTTOCONSOLE) {
														ssp--;
														scriptStringValues[ssp].printToConsole();
														continue;
													}
													if (opcode == LOGIN_UI_SETSPRITES) {
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
													if (opcode == LOGIN_UI_CLEAR) {
														LoginManager.method4637();
														ComponentList.hasScrollbar = false;
														continue;
													}
													if (opcode == SET_ANINT5794) {
														isp--; // Decrement then read
														anInt5794 = scriptIntValues[isp];
														continue;
													}
													if (opcode == MINIMAP_SETINTS) {
														isp -= 2;
														MiniMap.anInt4075 = scriptIntValues[isp];
														MiniMap.anInt5073 = scriptIntValues[isp + 1];
														continue;
													}
												} else if (opcode < 5600) {
													if (opcode == CAM_MOVETO) {
														isp -= 4;
														tempInt2 = scriptIntValues[isp];
														i = scriptIntValues[isp + 3];
														j = scriptIntValues[isp + 2];
														tempInt1 = scriptIntValues[isp + 1];
														Camera.setCameraTargetPosition(false, j, tempInt1, i, (tempInt2 & 0x3FFF) - Camera.sceneBaseTileZ, (tempInt2 >> 14 & 0x3FFF) - Camera.sceneBaseTileX);
														continue;
													}
													if (opcode == CAM_LOOKAT) {
                                                        // cam_lookat
                                                        // Point camera at a coordinate
														isp -= 4;
														tempInt1 = scriptIntValues[isp + 1]; // Camera height
														tempInt2 = scriptIntValues[isp]; // Packed coordinate
														i = scriptIntValues[isp + 3]; // Rotation speed
														j = scriptIntValues[isp + 2]; // Movement speed

                                                        // Unpack coordinate and convert to scene-relative
														Camera.setCameraLookAtTarget(tempInt1, (tempInt2 & 0x3FFF) - Camera.sceneBaseTileZ, j, (tempInt2 >> 14 & 0x3FFF) - Camera.sceneBaseTileX, i);
														continue;
													}
													if (opcode == CAM_MOVETO_SPLINE) {
                                                        // cam_moveto_spline
                                                        // Move camera along predefined spline path
														isp -= 6;
														tempInt2 = scriptIntValues[isp]; // Position spline ID
														if (tempInt2 >= 2) {
															throw new RuntimeException(); // Only 2 splines supported
														}
														Camera.cameraSplineId = tempInt2;

														tempInt1 = scriptIntValues[isp + 1]; // Starting keyframe index
														if (Camera.cameraSplines[Camera.cameraSplineId].length >> 1 <= tempInt1 + 1) {
															throw new RuntimeException(); // Keyframe out of range
														}

														Camera.positionKeyframe = tempInt1;
														Camera.animationTimer = 0; // Reset animation
														Camera.minAnimationSpeed = scriptIntValues[isp + 2]; // Min speed
														Camera.maxAnimationSpeed = scriptIntValues[isp + 3]; // Max speed
														j = scriptIntValues[isp + 4]; // Look-at spline ID
														if (j >= 2) {
															throw new RuntimeException();
														}
														Camera.lookAtSplineId = j;
														i = scriptIntValues[isp + 5]; // Look-at starting keyframe
														if (Camera.cameraSplines[Camera.lookAtSplineId].length >> 1 <= i + 1) {
															throw new RuntimeException();
														}
														Camera.lookAtKeyframe = i;
														Camera.cameraType = 3; // Spline camera mode
														continue;
													}
													if (opcode == CAM_RESET) {
														Camera.resetCameraEffects();
														continue;
													}
													if (opcode == CAM_SETANGLE) {
                                                        // cam_setangle
                                                        // Set manual camera pitch and yaw
														isp -= 2;
														Camera.orbitCameraPitch = scriptIntValues[isp]; // Vertical angle
														Camera.orbitCameraYaw = scriptIntValues[isp + 1]; // Horizontal angle
														if (Camera.cameraType == 2) {
                                                            // Apply immediately if in orbit mode
															Camera.cameraYaw = Camera.orbitCameraYaw;
															Camera.cameraPitch = Camera.orbitCameraPitch;
														}
														SceneCamera.clampCameraAngle(); // Prevent gimbal lock
														continue;
													}
													if (opcode == CAM_GETPITCH) {
														scriptIntValues[isp++] = Camera.orbitCameraPitch;
														continue;
													}
													if (opcode == CAM_GETYAW) {
														scriptIntValues[isp++] = Camera.orbitCameraYaw;
														continue;
													}
												} else if (opcode < 5700) {
													if (opcode == LOGIN) {
														ssp -= 2;
														tempString1 = scriptStringValues[ssp];
														chatTypedLowercase = scriptStringValues[ssp + 1];
														isp--; // Decrement then read
														j = scriptIntValues[isp];
														if (Client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0 && WorldList.step == 0) {
															LoginManager.login(tempString1, chatTypedLowercase, j);
														}
														continue;
													}
													if (opcode == LOGIN_ADVANCE) {
														LoginManager.advanceLoginStep();
														continue;
													}
													if (opcode == LOGIN_ABORT) {
														if (LoginManager.step == 0) {
															LoginManager.reply = -2;
														}
														continue;
													}
													if (opcode == CREATEACCOUNT_CHECKINFO) {
														isp -= 4;
														if (Client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0 && WorldList.step == 0) {
															CreateManager.checkInfo(scriptIntValues[isp + 2], scriptIntValues[isp + 3], scriptIntValues[isp], scriptIntValues[isp + 1]);
														}
														continue;
													}
													if (opcode == CREATEACCOUNT_CHECKNAME) {
														ssp--;
														if (Client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0 && WorldList.step == 0) {
															CreateManager.checkName(scriptStringValues[ssp].encode37());
														}
														continue;
													}
													if (opcode == CREATEACCOUNT_CREATE) {
														isp -= 4;
														ssp -= 2;
														if (Client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0 && WorldList.step == 0) {
															CreateManager.createAccount(scriptIntValues[isp], scriptIntValues[isp + 3], scriptIntValues[isp + 1], scriptStringValues[ssp + 1], scriptStringValues[ssp].encode37(), scriptIntValues[isp + 2]);
														}
														continue;
													}
													if (opcode == CREATEACCOUNT_ABORT) {
														if (CreateManager.step == 0) {
															CreateManager.reply = -2;
														}
														continue;
													}
													if (opcode == LOGIN_GETREPLY) {
														scriptIntValues[isp++] = LoginManager.reply;
														continue;
													}
													if (opcode == LOGIN_GETHOPTIME) {
														scriptIntValues[isp++] = LoginManager.hopTime;
														continue;
													}
													if (opcode == CREATEACCOUNT_GETREPLY) {
														scriptIntValues[isp++] = CreateManager.reply;
														continue;
													}
													if (opcode == CREATEACCOUNT_GETSUGGESTEDNAMES) {
														for (tempInt2 = 0; tempInt2 < 5; tempInt2++) {
															scriptStringValues[ssp++] = CreateManager.suggestedNames.length > tempInt2 ? CreateManager.suggestedNames[tempInt2].toTitleCase() : EMPTY_STRING;
														}
														CreateManager.suggestedNames = null;
														continue;
													}
													if (opcode == LOGIN_GETDISALLOWRESULT) {
														scriptIntValues[isp++] = LoginManager.disallowResult;
														continue;
													}
												} else if (opcode < 6100) {
													if (opcode == SETBRIGHTNESS) {
                                                        // setbrightness
                                                        // Set display brightness
														isp--; // Decrement then read
														int brightness = scriptIntValues[isp];

                                                        // Clamp to valid range
														if (brightness < 1) {
															brightness = 1; // Brightest
														}

														if (brightness > 4) {
															brightness = 4; // Darkest
                                                        }

														Preferences.brightness = brightness;

                                                        // Apply brightness to software renderer
														if (!GlRenderer.enabled || !Preferences.highDetailLighting) {
                                                            // Convert brightness level to gamma multiplier
															if (Preferences.brightness == 1) {
																Rasterizer.setBrightness(0.9F); // Brightest
															}
															if (Preferences.brightness == 2) {
																Rasterizer.setBrightness(0.8F);
															}
															if (Preferences.brightness == 3) {
																Rasterizer.setBrightness(0.7F);
															}
															if (Preferences.brightness == 4) {
																Rasterizer.setBrightness(0.6F); // Darkest
															}
														}

                                                        // Force fog update if OpenGL enabled
														if (GlRenderer.enabled) {
															FogManager.setInstantFade();
															if (!Preferences.highDetailLighting) {
																method2742(); // Reload game state for brightness change
															}
														}

														ObjTypeList.clearSprites(); // Clear item sprites (need re-rendering with new brightness)
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == SETLOWMEM) {
														isp--; // Decrement then read
														Preferences.setLowmem(scriptIntValues[isp] == 1);
														LocTypeList.clear();
														method2742();
														method2218();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == SETROOFSVISIBLE) {
														isp--; // Decrement then read
														Preferences.roofsVisible = scriptIntValues[isp] == 1;
														method2218();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == SETGROUNDDECOR) {
														isp--; // Decrement then read
														Preferences.showGroundDecorations = scriptIntValues[isp] == 1;
														method2742();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == SETHIGHTEXTURES) {
														isp--; // Decrement then read
														Preferences.highDetailTextures = scriptIntValues[isp] == 1;
														((Js5TextureProvider) Rasterizer.textureProvider).method3245(!Preferences.highDetailTextures);
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == SETMANYIDLEANIMS) {
														isp--; // Decrement then read
														Preferences.manyIdleAnimations = scriptIntValues[isp] == 1;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == SETFLICKERINGEFFECTS) {
														isp--; // Decrement then read
														Preferences.flickeringEffectsOn = scriptIntValues[isp] == 1;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == SETMANYGROUNDTEXTURES) {
														isp--; // Decrement then read
														Preferences.manyGroundTextures = scriptIntValues[isp] == 1;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == SETCHARACTERSHADOWS) {
														isp--; // Decrement then read
														Preferences.characterShadowsOn = scriptIntValues[isp] == 1;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == SETSCENERYSHADOWS) {
														isp--; // Decrement then read
														tempInt2 = scriptIntValues[isp];
														if (tempInt2 < 0 || tempInt2 > 2) {
															tempInt2 = 0;
														}
														Preferences.sceneryShadowsType = tempInt2;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == SETHIGHLIGHTING) {
														if (GlRenderer.enabled) {
															MaterialManager.setMaterial(0, 0);
														}
														isp--; // Decrement then read
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
													if (opcode == SETHIGHWATER) {
														isp--; // Decrement then read
														Preferences.highWaterDetail = scriptIntValues[isp] == 1;
														if (GlRenderer.enabled) {
															method2742();
														}
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == SETFOG) {
														isp--; // Decrement then read
														Preferences.fogEnabled = scriptIntValues[isp] == 1;
														if (GlRenderer.enabled) {
															FogManager.setInstantFade();
														}
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == SETANTIALIASING) {
														isp--; // Decrement then read
														tempInt2 = scriptIntValues[isp];
														if (GlRenderer.enabled) {
															GameShell.canvasReplaceRecommended = true;
														}
														if (tempInt2 < 0 || tempInt2 > 2) {
															tempInt2 = 0;
														}
														Preferences.antiAliasingMode = tempInt2;
														continue;
													}
													if (opcode == SETSTEREO) {
														isp--; // Decrement then read
														Preferences.stereo = scriptIntValues[isp] == 1;
														Client.method930();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == SETSOUNDVOLUME) {
														isp--; // Decrement then read
														tempInt2 = scriptIntValues[isp];
														if (tempInt2 < 0) {
															tempInt2 = 0;
														}
														if (tempInt2 > 127) {
															tempInt2 = 127;
														}
														Preferences.soundEffectVolume = tempInt2;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == SETMUSICVOLUME) {
														isp--; // Decrement then read
														tempInt2 = scriptIntValues[isp];
														if (tempInt2 < 0) {
															tempInt2 = 0;
														}
														if (tempInt2 > 255) {
															tempInt2 = 255;
														}
														if (tempInt2 != Preferences.musicVolume) {
															if (Preferences.musicVolume == 0 && MusicPlayer.groupId != -1) {
																MidiPlayer.playImmediate(Client.js5Archive6, MusicPlayer.groupId, tempInt2);
																MidiPlayer.jingle = false;
															} else if (tempInt2 == 0) {
																MidiPlayer.method4548();
																MidiPlayer.jingle = false;
															} else {
																MidiPlayer.method3956(tempInt2);
															}
															Preferences.musicVolume = tempInt2;
														}
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == SETAMBIENTSOUNDSVOLUME) {
														isp--; // Decrement then read
														tempInt2 = scriptIntValues[isp];
														if (tempInt2 < 0) {
															tempInt2 = 0;
														}
														if (tempInt2 > 127) {
															tempInt2 = 127;
														}
														Preferences.ambientSoundsVolume = tempInt2;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == SETNEVERREMOVINGROOFS) {
														isp--; // Decrement then read
														neverRemoveRoofs = scriptIntValues[isp] == 1;
														method2218();
														continue;
													}
													if (opcode == SETPARTICLES) {
														isp--; // Decrement then read
														tempInt2 = scriptIntValues[isp];
														if (tempInt2 < 0) {
															tempInt2 = 0;
														}
														if (tempInt2 > 2) {
															tempInt2 = 2;
														}
														local1552 = false;
														if (GameShell.maxMemory < 96) {
															local1552 = true;
															tempInt2 = 0;
														}
														Preferences.setParticles(tempInt2);
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														scriptIntValues[isp++] = local1552 ? 0 : 1;
														continue;
													}
													if (opcode == SETWINDOWMODE_PREF) {
														isp--; // Decrement then read
														tempInt2 = scriptIntValues[isp];
														if (tempInt2 < 0 || tempInt2 > 2) {
															tempInt2 = 0;
														}
														Preferences.windowMode = tempInt2;
														Preferences.write(GameShell.signLink);
														continue;
													}
													if (opcode == SETCURSORS) {
														isp--; // Decrement then read
														Preferences.cursorsEnabled = scriptIntValues[isp] != 0;
														Preferences.write(GameShell.signLink);
														continue;
													}
												} else if (opcode < 6200) {
													if (opcode == GETBRIGHTNESS) {
														scriptIntValues[isp++] = Preferences.brightness;
														continue;
													}
													if (opcode == GETLOWMEM) {
														scriptIntValues[isp++] = SceneGraph.allLevelsAreVisible() ? 1 : 0;
														continue;
													}
													if (opcode == GETROOFSVISIBLE) {
														scriptIntValues[isp++] = Preferences.roofsVisible ? 1 : 0;
														continue;
													}
													if (opcode == GETGROUNDDECOR) {
														scriptIntValues[isp++] = Preferences.showGroundDecorations ? 1 : 0;
														continue;
													}
													if (opcode == GETHIGHTEXTURES) {
														scriptIntValues[isp++] = Preferences.highDetailTextures ? 1 : 0;
														continue;
													}
													if (opcode == GETMANYIDLEANIMS) {
														scriptIntValues[isp++] = Preferences.manyIdleAnimations ? 1 : 0;
														continue;
													}
													if (opcode == GETFLICKERINGEFFECTS) {
														scriptIntValues[isp++] = Preferences.flickeringEffectsOn ? 1 : 0;
														continue;
													}
													if (opcode == GETMANYGROUNDTEXTURES) {
														scriptIntValues[isp++] = Preferences.manyGroundTextures ? 1 : 0;
														continue;
													}
													if (opcode == GETCHARACTERSHADOWS) {
														scriptIntValues[isp++] = Preferences.characterShadowsOn ? 1 : 0;
														continue;
													}
													if (opcode == GETSCENERYSHADOWS) {
														scriptIntValues[isp++] = Preferences.sceneryShadowsType;
														continue;
													}
													if (opcode == GETHIGHLIGHTING) {
														scriptIntValues[isp++] = Preferences.highDetailLighting ? 1 : 0;
														continue;
													}
													if (opcode == GETHIGHWATER) {
														scriptIntValues[isp++] = Preferences.highWaterDetail ? 1 : 0;
														continue;
													}
													if (opcode == GETFOG) {
														scriptIntValues[isp++] = Preferences.fogEnabled ? 1 : 0;
														continue;
													}
													if (opcode == GETANTIALIASING) {
														scriptIntValues[isp++] = Preferences.antiAliasingMode;
														continue;
													}
													if (opcode == GETSTEREO) {
														scriptIntValues[isp++] = Preferences.stereo ? 1 : 0;
														continue;
													}
													if (opcode == GETSOUNDVOLUME) {
														scriptIntValues[isp++] = Preferences.soundEffectVolume;
														continue;
													}
													if (opcode == GETMUSICVOLUME) {
														scriptIntValues[isp++] = Preferences.musicVolume;
														continue;
													}
													if (opcode == GETAMBIENTSOUNDSVOLUME) {
														scriptIntValues[isp++] = Preferences.ambientSoundsVolume;
														continue;
													}
													if (opcode == GETANTIALIASINGAVAILABLE) {
														if (GlRenderer.enabled) {
															scriptIntValues[isp++] = GlRenderer.arbMultisampleSupported ? 1 : 0;
														} else {
															scriptIntValues[isp++] = 0;
														}
														continue;
													}
													if (opcode == GETPARTICLES) {
														scriptIntValues[isp++] = Preferences.getParticleSetting();
														continue;
													}
													if (opcode == GETWINDOWMODE_PREF) {
														scriptIntValues[isp++] = Preferences.windowMode;
														continue;
													}
													if (opcode == GETCURSORS) {
														scriptIntValues[isp++] = Preferences.cursorsEnabled ? 1 : 0;
														continue;
													}
												} else if (opcode < 6300) {
													if (opcode == CAM_SETZOOMSCALE) {
														isp -= 2;
														minZoomScale = (short) scriptIntValues[isp];
														if (minZoomScale <= 0) {
															minZoomScale = 256;
														}
														maxZoomScale = (short) scriptIntValues[isp + 1];
														if (maxZoomScale <= 0) {
															maxZoomScale = 205;
														}
														continue;
													}
													if (opcode == CAM_SETDISTSCALE) {
														isp -= 2;
														minDistanceScale = (short) scriptIntValues[isp];
														if (minDistanceScale <= 0) {
															minDistanceScale = 256;
														}
														maxDistanceScale = (short) scriptIntValues[isp + 1];
														if (maxDistanceScale <= 0) {
															maxDistanceScale = 320;
														}
														continue;
													}
													if (opcode == CAM_SETCONSTRAINTS) {
														isp -= 4;
														minZoomConstraint = (short) scriptIntValues[isp];
														if (minZoomConstraint <= 0) {
															minZoomConstraint = 1;
														}
														maxZoomConstraint = (short) scriptIntValues[isp + 1];
														if (maxZoomConstraint <= 0) {
															maxZoomConstraint = 32767;
														} else if (minZoomConstraint > maxZoomConstraint) {
															maxZoomConstraint = minZoomConstraint;
														}
														minAspectRatio = (short) scriptIntValues[isp + 2];
														if (minAspectRatio <= 0) {
															minAspectRatio = 1;
														}
														maxAspectRatio = (short) scriptIntValues[isp + 3];
														if (maxAspectRatio <= 0) {
															maxAspectRatio = 32767;
														} else if (maxAspectRatio < minAspectRatio) {
															maxAspectRatio = minAspectRatio;
														}
														continue;
													}
													if (opcode == CAM_GETVIEWPORTSIZE) {
														method2314(ComponentList.specialComponent.width, 0, ComponentList.specialComponent.height, 0, false);
														scriptIntValues[isp++] = calculatedViewportWidth;
														scriptIntValues[isp++] = calculatedViewportHeight;
														continue;
													}
													if (opcode == CAM_GETDISTSCALE) {
														scriptIntValues[isp++] = minDistanceScale;
														scriptIntValues[isp++] = maxDistanceScale;
														continue;
													}
													if (opcode == CAM_GETZOOMSCALE) {
														scriptIntValues[isp++] = minZoomScale;
														scriptIntValues[isp++] = maxZoomScale;
														continue;
													}
												} else if (opcode < 6400) {
													if (opcode == CALC_TIME_MINUTES) {
                                                        // calc_time_minutes
														scriptIntValues[isp++] = (int) (MonotonicTime.currentTimeMillis() / 60000L);
														continue;
													}
													if (opcode == CALC_TIME_DAYS) {
                                                        // calc_time_days
														scriptIntValues[isp++] = (int) (MonotonicTime.currentTimeMillis() / 86400000L) - 11745;
														continue;
													}
													if (opcode == CALC_DATE) {
                                                        // calc_date
														isp -= 3;
														j = scriptIntValues[isp + 2];
														tempInt1 = scriptIntValues[isp + 1];
														tempInt2 = scriptIntValues[isp];
														calendar.clear();
														calendar.set(11, 12);
														calendar.set(j, tempInt1, tempInt2);
														scriptIntValues[isp++] = (int) (calendar.getTime().getTime() / 86400000L) - 11745;
														continue;
													}
													if (opcode == CALC_YEAR) {
														calendar.clear();
														calendar.setTime(new Date(MonotonicTime.currentTimeMillis()));
														scriptIntValues[isp++] = calendar.get(1);
														continue;
													}
													if (opcode == CALC_ISLEAPYEAR) {
														local1552 = true;
														isp--; // Decrement then read
														tempInt2 = scriptIntValues[isp];
														if (tempInt2 < 0) {
															local1552 = (tempInt2 + 1) % 4 == 0;
														} else if (tempInt2 < 1582) {
															local1552 = tempInt2 % 4 == 0;
														} else if (tempInt2 % 4 != 0) {
															local1552 = false;
														} else if (tempInt2 % 100 != 0) {
															local1552 = true;
														} else if (tempInt2 % 400 != 0) {
															local1552 = false;
														}
														scriptIntValues[isp++] = local1552 ? 1 : 0;
														continue;
													}
												} else if (opcode < 6500) {
													if (opcode == SHOWVIDEOAD) {
														// scriptIntValues[isp++] = client.showVideoAd() ? 1 : 0;
														scriptIntValues[isp++] = 0;
														continue;
													}
													if (opcode == ISSHOWINGVIDEOAD) {
														// scriptIntValues[isp++] = isShowingVideoAd() ? 1 : 0;
														scriptIntValues[isp++] = 0;
														continue;
													}
												} else if (opcode < 6600) {
													if (opcode == WORLDLIST_FETCH) {
														if (Client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0) {
															scriptIntValues[isp++] = WorldList.fetch() == -1 ? 0 : 1;
															continue;
														}
														scriptIntValues[isp++] = 1;
														continue;
													}
													@Pc(10247) WorldInfo worldInfo;
													@Pc(10191) World world;
													if (opcode == WORLDLIST_START) {
														world = WorldList.getFirstWorld();
														if (world == null) {
															scriptIntValues[isp++] = -1;
															scriptIntValues[isp++] = 0;
															scriptStringValues[ssp++] = EMPTY_STRING;
															scriptIntValues[isp++] = 0;
															scriptStringValues[ssp++] = EMPTY_STRING;
															scriptIntValues[isp++] = 0;
														} else {
															scriptIntValues[isp++] = world.id;
															scriptIntValues[isp++] = world.flags;
															scriptStringValues[ssp++] = world.activity;
															worldInfo = world.getWorldInfo();
															scriptIntValues[isp++] = worldInfo.flag;
															scriptStringValues[ssp++] = worldInfo.name;
															scriptIntValues[isp++] = world.players;
														}
														continue;
													}
													if (opcode == WORLDLIST_NEXT) {
														world = WorldList.getNextWorld();
														if (world == null) {
															scriptIntValues[isp++] = -1;
															scriptIntValues[isp++] = 0;
															scriptStringValues[ssp++] = EMPTY_STRING;
															scriptIntValues[isp++] = 0;
															scriptStringValues[ssp++] = EMPTY_STRING;
															scriptIntValues[isp++] = 0;
														} else {
															scriptIntValues[isp++] = world.id;
															scriptIntValues[isp++] = world.flags;
															scriptStringValues[ssp++] = world.activity;
															worldInfo = world.getWorldInfo();
															scriptIntValues[isp++] = worldInfo.flag;
															scriptStringValues[ssp++] = worldInfo.name;
															scriptIntValues[isp++] = world.players;
														}
														continue;
													}
													if (opcode == WORLDLIST_HOPWORLD) {
														isp--; // Decrement then read
														tempInt2 = scriptIntValues[isp];
														if (Client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0) {
															scriptIntValues[isp++] = WorldList.hopWorld(tempInt2) ? 1 : 0;
															continue;
														}
														scriptIntValues[isp++] = 0;
														continue;
													}
													if (opcode == WORLDLIST_SETLAST) {
														isp--; // Decrement then read
														Preferences.lastWorldId = scriptIntValues[isp];
														Preferences.write(GameShell.signLink);
														continue;
													}
													if (opcode == WORLDLIST_GETLAST) {
														scriptIntValues[isp++] = Preferences.lastWorldId;
														continue;
													}
													if (opcode == WORLDLIST_GETBYID) {
														isp--; // Decrement then read
														tempInt2 = scriptIntValues[isp];
														@Pc(10440) World targetWorld = getWorld(tempInt2);
														if (targetWorld == null) {
															scriptIntValues[isp++] = -1;
															scriptStringValues[ssp++] = EMPTY_STRING;
															scriptIntValues[isp++] = 0;
															scriptStringValues[ssp++] = EMPTY_STRING;
															scriptIntValues[isp++] = 0;
														} else {
															scriptIntValues[isp++] = targetWorld.flags;
															scriptStringValues[ssp++] = targetWorld.activity;
															@Pc(10458) WorldInfo targetWorldInfo = targetWorld.getWorldInfo();
															scriptIntValues[isp++] = targetWorldInfo.flag;
															scriptStringValues[ssp++] = targetWorldInfo.name;
															scriptIntValues[isp++] = targetWorld.players;
														}
														continue;
													}
													if (opcode == WORLDLIST_SORT) {
														isp -= 4;
														j = scriptIntValues[isp + 2];
														tempInt2 = scriptIntValues[isp];
														local7566 = scriptIntValues[isp + 3] == 1;
														local1552 = scriptIntValues[isp + 1] == 1;
														WorldList.sortWorldList(j, local1552, tempInt2, local7566);
														continue;
													}
												} else if (opcode < 6700) {
													if (opcode == SET_KEYBOARDCAMERA) {
														isp--; // Decrement then read
														Preferences.keyboardCameraEnabled = scriptIntValues[isp] == 1;
														Preferences.write(GameShell.signLink);
														continue;
													}
													if (opcode == GET_KEYBOARDCAMERA) {
														scriptIntValues[isp++] = Preferences.keyboardCameraEnabled ? 1 : 0;
														continue;
													}
												}
											}
										} else if (opcode == STRUCT_PARAM) {
											isp -= 2;
											tempInt2 = scriptIntValues[isp];
											tempInt1 = scriptIntValues[isp + 1];
											paramType = ParamTypeList.get(tempInt1);
											if (paramType.isString()) {
												scriptStringValues[ssp++] = StructTypeList.get(tempInt2).getParam(paramType.defaultString, tempInt1);
											} else {
												scriptIntValues[isp++] = StructTypeList.get(tempInt2).method2798(tempInt1, paramType.defaultInt);
											}
											continue;
										}
									} else if (opcode == LOC_PARAM) {
										isp -= 2;
										tempInt1 = scriptIntValues[isp + 1];
										tempInt2 = scriptIntValues[isp];
										paramType = ParamTypeList.get(tempInt1);
										if (paramType.isString()) {
											scriptStringValues[ssp++] = LocTypeList.get(tempInt2).getParam(paramType.defaultString, tempInt1);
										} else {
											scriptIntValues[isp++] = LocTypeList.get(tempInt2).getParam(paramType.defaultInt, tempInt1);
										}
										continue;
									}
								} else {
									if (opcode == APPEND_NUM) {
										// append_num
                                        // Append integer to string
										ssp--;
										tempString1 = scriptStringValues[ssp]; // Base string
										isp--; // Decrement then read
										tempInt1 = scriptIntValues[isp]; // Number to append
										scriptStringValues[ssp++] = JString.concatenate(new JString[] { tempString1, JString.parseInt(tempInt1) });
										continue;
									}
									if (opcode == APPEND) {
										// append
										ssp -= 2;
										chatTypedLowercase = scriptStringValues[ssp + 1];
										tempString1 = scriptStringValues[ssp];
										scriptStringValues[ssp++] = JString.concatenate(new JString[] { tempString1, chatTypedLowercase });
										continue;
									}
									if (opcode == APPEND_SIGNNUM) {
										// append_signnum
                                        // Append signed integer
										ssp--;
										tempString1 = scriptStringValues[ssp];
										isp--; // Decrement then read
										tempInt1 = scriptIntValues[isp];
										scriptStringValues[ssp++] = JString.concatenate(new JString[] { tempString1, JString.parseIntTrue(tempInt1) });
										continue;
									}
									if (opcode == LOWERCASE) {
										// lowercase
										ssp--;
										tempString1 = scriptStringValues[ssp];
										scriptStringValues[ssp++] = tempString1.toLowerCase();
										continue;
									}
									if (opcode == FROMDATE) {
										// fromdate
                                        // Convert day count to formatted date string (dd-MMM-yyyy)
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp]; // Days since reference date (Jan 1, 2002)
										@Pc(11770) long calculatedDate = (long) tempInt2 * 86400000L + 1014768000000L; // Convert to epoch millis
										calendar.setTime(new Date(calculatedDate));
										i = calendar.get(5); // Day of month
										start = calendar.get(2); // Month (0-11)
										childId = calendar.get(1); // Year
                                        // Format: "dd-MMM-yyyy" (e.g., "15-Jan-2009")
										scriptStringValues[ssp++] = JString.concatenate(new JString[] {
                                                JString.parseInt(i),
												ESCAPE_CHAR, // "-"
                                                DateUtil.aClass100Array40[start], // Month name
												ESCAPE_CHAR, // "-"
                                                JString.parseInt(childId)
                                        });
										continue;
									}
									if (opcode == TEXT_GENDER) {
										// text_gender
                                        // Select string based on player gender
										ssp -= 2;
										chatTypedLowercase = scriptStringValues[ssp + 1]; // Female text
										tempString1 = scriptStringValues[ssp]; // Male text
										if (PlayerList.self.appearance != null && PlayerList.self.appearance.gender) {
											scriptStringValues[ssp++] = chatTypedLowercase; // Player is female
											continue;
										}
										scriptStringValues[ssp++] = tempString1; // Player is male
										continue;
									}
									if (opcode == TOSTRING) {
										// tostring
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptStringValues[ssp++] = JString.parseInt(tempInt2);
										continue;
									}
									if (opcode == COMPARE) {
										// compare
                                        // String comparison
										ssp -= 2;
										scriptIntValues[isp++] = scriptStringValues[ssp].compare(scriptStringValues[ssp + 1]);
										continue;
									}
									if (opcode == PARAHEIGHT) {
                                        // paraheight
                                        // Calculate paragraph height in pixels
										ssp--;
										tempString1 = scriptStringValues[ssp];
										isp -= 2;
										j = scriptIntValues[isp + 1]; // Font ID
										tempInt1 = scriptIntValues[isp]; // Max width
										scriptIntValues[isp++] = FontMetricsList.get(j).getParagraphLineCount(tempString1, tempInt1);
										continue;
									}
									if (opcode == PARAWIDTH) {
                                        // parawidth
                                        // Calculate maximum line width in paragraph
										isp -= 2;
										ssp--;
										tempString1 = scriptStringValues[ssp];
										j = scriptIntValues[isp + 1]; // Font ID
										tempInt1 = scriptIntValues[isp]; // Max width
										scriptIntValues[isp++] = FontMetricsList.get(j).getMaxLineWidth(tempString1, tempInt1);
										continue;
									}
									if (opcode == TEXT_SWITCH) {
                                        // text_switch
                                        // Conditional string selection
										ssp -= 2;
										tempString1 = scriptStringValues[ssp];
										chatTypedLowercase = scriptStringValues[ssp + 1];
										isp--; // Decrement then read
										if (scriptIntValues[isp] == 1) {
											scriptStringValues[ssp++] = tempString1;
										} else {
											scriptStringValues[ssp++] = chatTypedLowercase;
										}
										continue;
									}
									if (opcode == ESCAPE) {
                                        // escape
                                        // Escape special characters for display
										ssp--;
										tempString1 = scriptStringValues[ssp];
										scriptStringValues[ssp++] = Font.escape(tempString1);
										continue;
									}
									if (opcode == APPEND_CHAR) {
                                        // append_char
                                        // Append character to string
										ssp--;
										tempString1 = scriptStringValues[ssp];
										isp--; // Decrement then read
										tempInt1 = scriptIntValues[isp]; // Character code
										if (tempInt1 == -1) {
											throw new RuntimeException("null char"); // Invalid character
                                        }
										scriptStringValues[ssp++] = tempString1.concatChar(tempInt1);
										continue;
									}
									if (opcode == CHAR_ISVALID) {
                                        // char_isvalid
                                        // Check if character code is valid
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = CharUtils.isValidChar(tempInt2) ? 1 : 0;
										continue;
									}
									if (opcode == CHAR_ISOTHER) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = CharUtils.method433(tempInt2) ? 1 : 0;
										continue;
									}
									if (opcode == CHAR_ISLETTER) {
                                        // char_isletter
                                        // Check if character is a letter
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = CharUtils.isLetter(tempInt2) ? 1 : 0;
										continue;
									}
									if (opcode == CHAR_ISDIGIT) {
                                        // char_isdigit
                                        // Check if character is a digit
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = CharUtils.isDigit(tempInt2) ? 1 : 0;
										continue;
									}
									if (opcode == STRING_LENGTH) {
										ssp--;
										tempString1 = scriptStringValues[ssp];
										if (tempString1 == null) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = tempString1.length();
										}
										continue;
									}
									if (opcode == SUBSTRING) {
										isp -= 2;
										ssp--;
										tempString1 = scriptStringValues[ssp];
										tempInt1 = scriptIntValues[isp];
										j = scriptIntValues[isp + 1];
										scriptStringValues[ssp++] = tempString1.substring(j, tempInt1);
										continue;
									}
									if (opcode == REMOVETAGS) {
										ssp--;
										tempString1 = scriptStringValues[ssp];
										chatTypedLowercase = JString.allocate(tempString1.length());
										@Pc(12220) boolean tradeFilterChanged = false;
										for (i = 0; i < tempString1.length(); i++) {
											start = tempString1.charAt(i);
											if (start == 60) {
												tradeFilterChanged = true;
											} else if (start == 62) {
												tradeFilterChanged = false;
											} else if (!tradeFilterChanged) {
												chatTypedLowercase.append(start);
											}
										}
										chatTypedLowercase.method3156();
										scriptStringValues[ssp++] = chatTypedLowercase;
										continue;
									}
									if (opcode == STRING_INDEXOF_CHAR) {
										isp -= 2;
										ssp--;
										tempString1 = scriptStringValues[ssp];
										tempInt1 = scriptIntValues[isp];
										j = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = tempString1.indexOf(tempInt1, j);
										continue;
									}
									if (opcode == STRING_INDEXOF_STRING) {
										ssp -= 2;
										tempString1 = scriptStringValues[ssp];
										chatTypedLowercase = scriptStringValues[ssp + 1];
										isp--; // Decrement then read
										j = scriptIntValues[isp];
										scriptIntValues[isp++] = tempString1.indexOf(chatTypedLowercase, j);
										continue;
									}
									if (opcode == CHAR_TOLOWERCASE) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = CharUtils.toLowerCase(tempInt2);
										continue;
									}
									if (opcode == CHAR_TOUPPERCASE) {
										isp--; // Decrement then read
										tempInt2 = scriptIntValues[isp];
										scriptIntValues[isp++] = CharUtils.toUpperCase(tempInt2);
										continue;
									}
									if (opcode == FORMATNUMBER) {
										isp--; // Decrement then read
										isFemale = scriptIntValues[isp] != 0;
										isp--; // Decrement then read
										tempInt1 = scriptIntValues[isp];
										scriptStringValues[ssp++] = StringUtils.formatNumber(Client.language, isFemale, 0, (long) tempInt1);
										continue;
									}
								}
							}
						} else {
							if (opcode < 2000) {
								createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
							} else {
								isp--; // Decrement then read
								createdComponent = ComponentList.getComponent(scriptIntValues[isp]);
								opcode -= 1000;
							}
							if (opcode == CC_SETPOSITION) {
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
								tempInt1 = scriptIntValues[isp + 2];
								if (tempInt1 < 0) {
									tempInt1 = 0;
								} else if (tempInt1 > 5) {
									tempInt1 = 5;
								}
								createdComponent.xMode = (byte) j;
								createdComponent.yMode = (byte) tempInt1;
								ComponentList.redraw(createdComponent);
								ComponentList.update(createdComponent);
								if (createdComponent.createdComponentId == -1) {
									ClientServerStateSync.setComponentPositionClient(createdComponent.id);
								}
								continue;
							}
							if (opcode == CC_SETSIZE) {
								// setsize
								isp -= 4;
								createdComponent.baseWidth = scriptIntValues[isp];
								createdComponent.baseHeight = scriptIntValues[isp + 1];
								createdComponent.maxModelSize = 0;
								createdComponent.anInt526 = 0;
								tempInt1 = scriptIntValues[isp + 2];
								j = scriptIntValues[isp + 3];
								if (j < 0) {
									j = 0;
								} else if (j > 4) {
									j = 4;
								}
								createdComponent.dynamicHeightValue = (byte) j;
								if (tempInt1 < 0) {
									tempInt1 = 0;
								} else if (tempInt1 > 4) {
									tempInt1 = 4;
								}
								createdComponent.dynamicWidthValue = (byte) tempInt1;
								ComponentList.redraw(createdComponent);
								ComponentList.update(createdComponent);
								if (createdComponent.type == 0) {
									ComponentList.updateContainerLayout(createdComponent, false);
								}
								continue;
							}
							if (opcode == CC_SETHIDE) {
								// sethide
								isp--; // Decrement then read
								local1552 = scriptIntValues[isp] == 1;
								if (local1552 != createdComponent.hidden) {
									createdComponent.hidden = local1552;
									ComponentList.redraw(createdComponent);
								}
								if (createdComponent.createdComponentId == -1) {
									ClientServerStateSync.setComponentHiddenClient(createdComponent.id);
								}
								continue;
							}
							if (opcode == CC_SETASPECT) {
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
							if (opcode == CC_SETNOCLICK) {
								isp--; // Decrement then read
								createdComponent.noClickThrough = scriptIntValues[isp] == 1;
								continue;
							}
						}
					}
				}
				throw new IllegalStateException();
			}
		} catch (@Pc(14378) Exception exception) {
            // ClientScript error handling,  builds descriptive error messages with call stack
			if (clientScript.name == null) {
                // Unnamed script
				if (Client.modeWhere != 0) {
                    // Show error in chat if not live
					Chat.addMessage(EMPTY_STRING, 0, CS_ERROR);
				}
				TracingException.report("CS2 - scr:" + clientScript.nodeId + " op:" + op, exception);
			} else {

                // Named script, build detailed error message with call stack
				@Pc(14385) JString errorMessage = JString.allocate(30);
				errorMessage.method3113(ERROR_IN_SCRIPT).method3113(clientScript.name); // "in: scriptname"

                // Add call stack trace (gosub chain)
				for (cycles = fp - 1; cycles >= 0; cycles--) {
					errorMessage.method3113(ERROR_VIA).method3113(callStack[cycles].script.name); // "via: caller"
				}

                // Special case: gosub error (script not found)
				if (op == 40) { // Opcode 40 = gosub_with_params
					cycles = intOperands[pc]; // Script ID that failed to load
					errorMessage.method3113(ERROR_MISSING_GOSUB).method3113(JString.parseInt(cycles));
				}

				if (Client.modeWhere != 0) {
                    // Show simplified error in chat
					Chat.addMessage(EMPTY_STRING, 0, JString.concatenate(new JString[] {ERROR_PREFIX, clientScript.name}));
				}

                // Report full error with stack trace
				TracingException.report("CS2 - scr:" + clientScript.nodeId + " op:" + op + new String(errorMessage.method3148()), exception);
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
		@Pc(38) int local38 = SceneGraph.getTileHeight(Player.currentLevel, arg5, arg2) - arg3;
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
			@Pc(51) int local51 = local25 * (maxZoomScale - minZoomScale) / 100 + minZoomScale;
			if (minZoomConstraint > local51) {
				local51 = minZoomConstraint;
			} else if (maxZoomConstraint < local51) {
				local51 = maxZoomConstraint;
			}
			@Pc(73) int local73 = local51 * arg2 * 512 / (arg0 * 334);
			@Pc(115) int local115;
			@Pc(122) int local122;
			@Pc(86) short local86;
			if (local73 < minAspectRatio) {
				local86 = minAspectRatio;
				local51 = arg0 * 334 * local86 / (arg2 * 512);
				if (maxZoomConstraint < local51) {
					local51 = maxZoomConstraint;
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
			} else if (maxAspectRatio < local73) {
				local86 = maxAspectRatio;
				local51 = local86 * arg0 * 334 / (arg2 * 512);
				if (minZoomConstraint > local51) {
					local51 = minZoomConstraint;
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
		calculatedViewportWidth = (short) arg0;
		calculatedViewportHeight = (short) arg2;
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
					player.groundHeight = SceneGraph.getTileHeight(Player.currentLevel, player.xFine, player.zFine);
					SceneGraph.addTemporary(Player.currentLevel, player.xFine, player.zFine, player.groundHeight, (sceneZ - 1) * 64 + 60, player, player.orientation, entityBitset, player.seqStretches);
				} else {
					player.lowMemory = false;
					player.groundHeight = SceneGraph.getTileHeight(Player.currentLevel, player.xFine, player.zFine);
					addTemporary(Player.currentLevel, player.xFine, player.zFine, player.groundHeight, player, player.orientation, entityBitset, player.atachmentX0, player.attachmentZ0, player.attachmentX1, player.attachmentZ1);
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
				npc.groundHeight = SceneGraph.getTileHeight(Player.currentLevel, npc.xFine, npc.zFine);
				SceneGraph.addTemporary(Player.currentLevel, npc.xFine, npc.zFine, npc.groundHeight, npcSize * 64 + 60 - 64, npc, npc.orientation, entityBitset, npc.seqStretches);
			}
		}
	}

	//TODO move somewhere else
	@OriginalMember(owner = "client!pk", name = "i", descriptor = "(I)V")
	public static void updateSceneProjectiles() {
		for (@Pc(16) ProjAnimNode projectileNode = (ProjAnimNode) SceneGraph.projectiles.head(); projectileNode != null; projectileNode = (ProjAnimNode) SceneGraph.projectiles.next()) {
			@Pc(21) ProjectileAnimation projectile = projectileNode.value;
			if (Player.currentLevel != projectile.currentPlane || projectile.lastCycle < Client.loop) {
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
					if (PlayerList.localPid == playerIndex) {
						player = PlayerList.self;
					} else {
						player = PlayerList.players[playerIndex];
					}
					if (player != null && player.xFine >= 0 && player.xFine < 13312 && player.zFine >= 0 && player.zFine < 13312) {
						projectile.setTarget(player.zFine, Client.loop, SceneGraph.getTileHeight(projectile.currentPlane, player.xFine, player.zFine) - projectile.baseZ, player.xFine);
					}
				}
				projectile.update(Protocol.sceneDelta);
				SceneGraph.addTemporary(Player.currentLevel, (int) projectile.x, (int) projectile.y, (int) projectile.z, 60, projectile, projectile.yaw, -1L, false);
			}
		}
	}

	//TODO move somewhere else
	@OriginalMember(owner = "client!u", name = "a", descriptor = "(Z)V")
	public static void updateSpotAnims() {
		for (@Pc(9) SpotAnimEntity spotAnimNode = (SpotAnimEntity) SceneGraph.spotanims.head(); spotAnimNode != null; spotAnimNode = (SpotAnimEntity) SceneGraph.spotanims.next()) {
			@Pc(15) SpotAnim animation = spotAnimNode.animation;
			if (animation.level != Player.currentLevel || animation.seqComplete) {
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
		if (markValue == tileMarkings[Player.currentLevel][startX][startZ]) {
			return false;
		} else if ((SceneGraph.renderFlags[Player.currentLevel][startX][startZ] & 0x4) == 0) {
			return false;
		} else {
			@Pc(47) int queueRead = 0;
			@Pc(49) byte queueStart = 0;
			PathFinder.bfsStepX[0] = startX;
			@Pc(69) int queueWrite = queueStart + 1;
			PathFinder.bfsStepZ[0] = startZ;
			tileMarkings[Player.currentLevel][startX][startZ] = markValue;
			while (queueRead != queueWrite) {
				@Pc(94) int direction1 = PathFinder.bfsStepX[queueRead] >> 16 & 0xFF;
				@Pc(102) int direction2 = PathFinder.bfsStepX[queueRead] >> 24 & 0xFF;
				@Pc(108) int currentX = PathFinder.bfsStepX[queueRead] & 0xFFFF;
				@Pc(116) int direction3 = PathFinder.bfsStepZ[queueRead] >> 16 & 0xFF;
				@Pc(122) int currentZ = PathFinder.bfsStepZ[queueRead] & 0xFFFF;
				queueRead = queueRead + 1 & 0xFFF;
				@Pc(130) boolean isBlocked = false;
				@Pc(132) boolean foundVisible = false;
				if ((SceneGraph.renderFlags[Player.currentLevel][currentX][currentZ] & 0x4) == 0) {
					isBlocked = true;
				}
				@Pc(150) int level;
				@Pc(191) int local191;
				label238: for (level = Player.currentLevel + 1; level <= 3; level++) {
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
					if (SceneGraph.tileHeights[Player.currentLevel + 1][currentX][currentZ] > maxHeights[regionIndex]) {
						maxHeights[regionIndex] = SceneGraph.tileHeights[Player.currentLevel + 1][currentX][currentZ];
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
					if (currentX >= 1 && tileMarkings[Player.currentLevel][currentX - 1][currentZ] != markValue) {
						PathFinder.bfsStepX[queueWrite] = currentX - 1 | 0x120000 | 0xD3000000;
						PathFinder.bfsStepZ[queueWrite] = currentZ | 0x130000;
						queueWrite = queueWrite + 1 & 0xFFF;
						tileMarkings[Player.currentLevel][currentX - 1][currentZ] = markValue;
					}
					currentZ++;
					if (currentZ < 104) {
						if (currentX - 1 >= 0 && markValue != tileMarkings[Player.currentLevel][currentX - 1][currentZ] && (SceneGraph.renderFlags[Player.currentLevel][currentX][currentZ] & 0x4) == 0 && (SceneGraph.renderFlags[Player.currentLevel][currentX - 1][currentZ - 1] & 0x4) == 0) {
							PathFinder.bfsStepX[queueWrite] = 0x52000000 | 0x120000 | currentX - 1;
							PathFinder.bfsStepZ[queueWrite] = currentZ | 0x130000;
							tileMarkings[Player.currentLevel][currentX - 1][currentZ] = markValue;
							queueWrite = queueWrite + 1 & 0xFFF;
						}
						if (markValue != tileMarkings[Player.currentLevel][currentX][currentZ]) {
							PathFinder.bfsStepX[queueWrite] = currentX | 0x13000000 | 0x520000;
							PathFinder.bfsStepZ[queueWrite] = currentZ | 0x530000;
							queueWrite = queueWrite + 1 & 0xFFF;
							tileMarkings[Player.currentLevel][currentX][currentZ] = markValue;
						}
						if (currentX + 1 < 104 && tileMarkings[Player.currentLevel][currentX + 1][currentZ] != markValue && (SceneGraph.renderFlags[Player.currentLevel][currentX][currentZ] & 0x4) == 0 && (SceneGraph.renderFlags[Player.currentLevel][currentX + 1][currentZ - 1] & 0x4) == 0) {
							PathFinder.bfsStepX[queueWrite] = 0x92000000 | 0x520000 | currentX + 1;
							PathFinder.bfsStepZ[queueWrite] = currentZ | 0x530000;
							tileMarkings[Player.currentLevel][currentX + 1][currentZ] = markValue;
							queueWrite = queueWrite + 1 & 0xFFF;
						}
					}
					currentZ--;
					if (currentX + 1 < 104 && markValue != tileMarkings[Player.currentLevel][currentX + 1][currentZ]) {
						PathFinder.bfsStepX[queueWrite] = currentX + 1 | 0x920000 | 0x53000000;
						PathFinder.bfsStepZ[queueWrite] = currentZ | 0x930000;
						tileMarkings[Player.currentLevel][currentX + 1][currentZ] = markValue;
						queueWrite = queueWrite + 1 & 0xFFF;
					}
					currentZ--;
					if (currentZ >= 0) {
						if (currentX - 1 >= 0 && tileMarkings[Player.currentLevel][currentX - 1][currentZ] != markValue && (SceneGraph.renderFlags[Player.currentLevel][currentX][currentZ] & 0x4) == 0 && (SceneGraph.renderFlags[Player.currentLevel][currentX - 1][currentZ + 1] & 0x4) == 0) {
							PathFinder.bfsStepX[queueWrite] = currentX - 1 | 0xD20000 | 0x12000000;
							PathFinder.bfsStepZ[queueWrite] = currentZ | 0xD30000;
							tileMarkings[Player.currentLevel][currentX - 1][currentZ] = markValue;
							queueWrite = queueWrite + 1 & 0xFFF;
						}
						if (markValue != tileMarkings[Player.currentLevel][currentX][currentZ]) {
							PathFinder.bfsStepX[queueWrite] = currentX | 0xD20000 | 0x93000000;
							PathFinder.bfsStepZ[queueWrite] = currentZ | 0xD30000;
							queueWrite = queueWrite + 1 & 0xFFF;
							tileMarkings[Player.currentLevel][currentX][currentZ] = markValue;
						}
						if (currentX + 1 < 104 && tileMarkings[Player.currentLevel][currentX + 1][currentZ] != markValue && (SceneGraph.renderFlags[Player.currentLevel][currentX][currentZ] & 0x4) == 0 && (SceneGraph.renderFlags[Player.currentLevel][currentX + 1][currentZ + 1] & 0x4) == 0) {
							PathFinder.bfsStepX[queueWrite] = currentX + 1 | 0xD2000000 | 0x920000;
							PathFinder.bfsStepZ[queueWrite] = currentZ | 0x930000;
							tileMarkings[Player.currentLevel][currentX + 1][currentZ] = markValue;
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
		@Pc(8) JString modeString = URL_DOMAIN_WWW;
		@Pc(10) JString settingsParam = JString.EMPTY;
		if (Client.modeWhere != 0) {
			modeString = URL_DOMAIN_WTQA;
		}
		if (Client.settings != null) {
			settingsParam = JString.concatenate(new JString[] {URL_SETTINGS_PARAM, Client.settings});
		}
		return JString.concatenate(new JString[] {URL_PROTOCOL_HTTP, modeString, URL_RUNESCAPE_LANG_PARAM, JString.parseInt(Client.language), URL_AFFILIATE_PARAM, JString.parseInt(Client.affiliate), settingsParam, URL_CONFIG_SUFFIX});
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
		if (Player.currentLevel == 3) {
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
			local33 = SceneGraph.getTileHeight(Player.currentLevel, Camera.renderX, Camera.renderZ);
			if (local33 - Camera.cameraY < 800 && (SceneGraph.renderFlags[Player.currentLevel][Camera.renderX >> 7][Camera.renderZ >> 7] & 0x4) != 0) {
				calculateVisibleRegion(false, Camera.renderX >> 7, Camera.renderZ >> 7, SceneGraph.tiles, 1);
			}
			return;
		}
		if ((SceneGraph.renderFlags[Player.currentLevel][PlayerList.self.xFine >> 7][PlayerList.self.zFine >> 7] & 0x4) != 0) {
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
				if ((SceneGraph.renderFlags[Player.currentLevel][local33][local40] & 0x4) != 0) {
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
					if ((SceneGraph.renderFlags[Player.currentLevel][local33][local40] & 0x4) != 0) {
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
			if ((SceneGraph.renderFlags[Player.currentLevel][local33][local40] & 0x4) != 0) {
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
				if ((SceneGraph.renderFlags[Player.currentLevel][local33][local40] & 0x4) != 0) {
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
		if (menuY + menuHeight > GameShell.canvasHeight) {
			menuY = GameShell.canvasHeight - menuHeight;
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
}

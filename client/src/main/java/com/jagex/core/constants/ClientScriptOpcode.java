package com.jagex.core.constants;

public final class ClientScriptOpcode {
    
	// CORE LANGUAGE OPERATIONS (0-99)

	// Push integer constant from operands onto stack 
	public static final int PUSH_CONSTANT_INT = 0;

	// Push VarP (player variable) value onto stack 
	public static final int PUSH_VARP = 1;

	// Set VarP value from stack
	public static final int POP_VARP = 2;

	// Push string constant from operands
	public static final int PUSH_CONSTANT_STRING = 3;

	// Count items of specific type in inventory 
	public static final int LOAD_INV_COUNT = 4;

	// Load variable onto stack 
	public static final int LOAD_VAR = 5;

	public static final int BRANCH = 6;

	public static final int BRANCH_NOT = 7;

	public static final int BRANCH_EQUALS = 8;

	public static final int BRANCH_LESS_THAN = 9;

	public static final int BRANCH_GREATER_THAN = 10;





	// Get XP required for next level
	public static final int LOAD_NEXT_LEVEL_XP = 6;

	// Load VarP as percentage
	public static final int LOAD_VARP_PERCENT = 7;

	// Get player combat level
	public static final int LOAD_COMBAT_LEVEL = 8;

	// Get player total level
	public static final int LOAD_TOTAL_LEVEL = 9;

	// Check if inventory contains item
	public static final int LOAD_INV_CONTAINS = 10;

	// Get run energy percentage
	public static final int LOAD_ENERGY = 11;

	// Get weight carried by player
	public static final int LOAD_WEIGHT = 12;

	// Extract bit from VarP as boolean
	public static final int LOAD_BOOL = 13;

	// Get VarBit value
	public static final int LOAD_VARBIT = 14;

	// Set accumulator mode to subtract
	public static final int OP_SUBTRACT = 15;

	// Set accumulator mode to divide
	public static final int OP_DIVIDE = 16;

	// Set accumulator mode to multiply
	public static final int OP_MULTIPLY = 17;

	// Get player X coordinate
	public static final int COORD_X = 18;

	// Get player Z coordinate
	public static final int COORD_Z = 19;

	// Push constant value
	public static final int PUSH_CONSTANT = 20;

	// Return from gosub/proc call 
	public static final int RETURN = 21;

	// Push VarBit value onto stack 
	public static final int POP_VARBIT = 25;

	// Set VarBit value from stack 
	public static final int PUSH_VARBIT = 27;

	// Branch if value <= comparison 
	public static final int BRANCH_LESS_THAN_OR_EQUALS = 31;

	// Branch if value >= comparison 
	public static final int BRANCH_GREATER_THAN_OR_EQUALS = 32;

	// Push integer local variable 
	public static final int PUSH_INT_LOCAL = 33;

	// Pop to integer local variable 
	public static final int POP_INT_LOCAL = 34;

	// Push string local variable 
	public static final int PUSH_STRING_LOCAL = 35;

	// Pop to string local variable 
	public static final int POP_STRING_LOCAL = 36;

	// Concatenate N strings from stack 
	public static final int JOIN_STRING = 37;

	// Discard top integer from stack 
	public static final int POP_INT_DISCARD = 38;

	// Discard top string from stack 
	public static final int POP_STRING_DISCARD = 39;

	// Call another script/proc with parameters (gosub) 
	public static final int GOSUB_WITH_PARAMS = 40;

	// Push VarC (client variable) integer 
	public static final int PUSH_VARC_INT = 42;

	// Set VarC integer from stack 
	public static final int POP_VARC_INT = 43;

	// Define/initialize array 
	public static final int DEFINE_ARRAY = 44;

	// Get array element 
	public static final int PUSH_ARRAY_INT = 45;

	// Set array element 
	public static final int POP_ARRAY_INT = 46;

	// Push VarC string 
	public static final int PUSH_VARC_STRING = 47;

	// Set VarC string from stack 
	public static final int POP_VARC_STRING = 48;

	// Switch table jump 
	public static final int SWITCH = 51;

	// COMPONENT CREATION/MANAGEMENT (100-299)

	// Create dynamic component 
	public static final int CC_CREATE = 100;

	// Delete dynamic component 
	public static final int CC_DELETE = 101;

	// Delete all child components 
	public static final int CC_DELETEALL = 102;

	// Find component and set as active 
	public static final int CC_FIND = 200;

	// Find interface component 
	public static final int IF_FIND = 201;

	// PLAYER APPEARANCE (400-499)

	// Set identity kit (body part) 
	public static final int BASEIDKIT = 403;

	// Set player appearance color 
	public static final int BASECOLOR = 404;

	// Set player gender 
	public static final int SETGENDER = 410;

	// COMPONENT PROPERTIES - BASIC (1000-1099)

	// Set component position and alignment 
	public static final int CC_SETPOSITION = 1000;

	// Set component size and scaling mode 
	public static final int CC_SETSIZE = 1001;

	// Show/hide component 
	public static final int CC_SETHIDE = 1003;

	// Set component aspect ratio 
	public static final int CC_SETASPECT = 1004;

	// Set click-through behavior 
	public static final int CC_SETNOCLICK = 1005;

	// COMPONENT PROPERTIES - VISUAL (1100-1199)

	// Set scroll position 
	public static final int CC_SETSCROLLPOS = 1100;

	// Set component color (RGB) 
	public static final int CC_SETCOLOR = 1101;

	// Set filled state 
	public static final int CC_SETFILL = 1102;

	// Set transparency/alpha (0-255) 
	public static final int CC_SETTRANS = 1103;

	// Set line width 
	public static final int CC_SETLINEWID = 1104;

	// Set sprite graphic 
	public static final int CC_SETGRAPHIC = 1105;

	// Set 2D rotation angle 
	public static final int CC_SETANGLE = 1106;

	// Set sprite tiling mode 
	public static final int CC_SETTILING = 1107;

	// Set 3D model 
	public static final int CC_SETMODEL = 1108;

	// Set 3D model rotation and position 
	public static final int CC_SETMODELANGLE = 1109;

	// Set model animation sequence 
	public static final int CC_SETMODELANIM = 1110;

	// Set orthographic projection mode 
	public static final int CC_SETMODELORTHOG = 1111;

	// Set component text 
	public static final int CC_IF_SETTEXT = 1112;

	// Set text font 
	public static final int CC_IF_SETTEXTFONT = 1113;

	// Set text alignment (left/center/right) 
	public static final int CC_IF_SETTEXTALIGN = 1114;

	// Enable text shadow 
	public static final int CC_IF_SETTEXTSHADOW = 1115;

	// Set text outline thickness 
	public static final int CC_IF_SETOUTLINE = 1116;

	// Set shadow color 
	public static final int CC_IF_SETGRAPHICSHADOW = 1117;

	// Set vertical flip 
	public static final int CC_IF_SETHFLIP = 1118;

	// Set horizontal flip 
	public static final int CC_IF_SETVFLIP = 1119;

	// Set scroll area size 
	public static final int CC_IF_SETSCROLLSIZE = 1120;

	// Set component shorts (unknown purpose) 
	public static final int CC_IF_SETCOMPONENTSHORTS = 1121;

	// Set alpha channel flag 
	public static final int CC_IF_SETALPHA = 1122;

	// Set model zoom level 
	public static final int CC_IF_SETMODELZOOM = 1123;

	// COMPONENT PROPERTIES - MODELS/OBJECTS (1200-1299)

	// Display item model with count text 
	public static final int CC_IF_SETOBJECT = 1200;

	// Display NPC chathead model 
	public static final int CC_IF_SETNPCHEAD = 1201;

	// Display player's own chathead 
	public static final int CC_IF_SETPLAYERHEAD_SELF = 1202;

	// Display full NPC model 
	public static final int CC_IF_SETNPCMODEL = 1203;

	// Display player model 
	public static final int CC_IF_SETPLAYERMODEL = 1204;

	// Display item model without count text 
	public static final int CC_IF_SETOBJECT_NONUM = 1205;

	// COMPONENT PROPERTIES - ADVANCED (1300-1399)

	// Set component right-click option text 
	public static final int CC_IF_SETOP = 1300;

	// Set component parent container 
	public static final int CC_SETPARENT = 1301;

	// Set drag render behavior mode 
	public static final int CC_IF_SETDRAGRENDERBEHAVIOUR = 1302;

	// Set drag dead zone in pixels 
	public static final int CC_IF_SETDRAGDEADZONE = 1303;

	// Set drag dead time in client ticks 
	public static final int CC_IF_SETDRAGDEADTIME = 1304;

	// Set option prefix text (e.g., "Use") 
	public static final int CC_IF_SETOPBASE = 1305;

	// Set option suffix text 
	public static final int CC_IF_SETTARGETVERB = 1306;

	// Clear all right-click options 
	public static final int CC_IF_CLEAROPS = 1307;

	// Set component ints (unknown purpose) 
	public static final int CC_IF_SETTARGETCURSORS = 1308;

	// Set component property (unknown purpose) 
	public static final int CC_IF_SETOPCURSOR = 1309;

	// COMPONENT EVENT HANDLERS (1400-1499)

	// Set click repeat handler script 
	public static final int CC_IF_SETONCLICK = 1400;

	// Set hold handler script 
	public static final int CC_IF_SETONHOLD = 1401;

	// Set mouse release handler script 
	public static final int CC_IF_SETONRELEASE = 1402;

	// Set mouse over handler script 
	public static final int CC_IF_SETONMOUSEOVER = 1403;

	// Set mouse leave handler script 
	public static final int CC_IF_SETONMOUSELEAVE = 1404;

	// Set drag start handler script 
	public static final int CC_IF_SETONDRAG = 1405;

	// Set use-with handler script 
	public static final int CC_IF_SETONTARGETLEAVE = 1406;

	// Set VarP transmit handler script 
	public static final int CC_IF_SETONVARTRANSMIT = 1407;

	// Set timer handler script 
	public static final int CC_IF_SETONTIMER = 1408;

	// Set option click handler script 
	public static final int CC_IF_SETONOP = 1409;

	// Set drag release handler script 
	public static final int CC_IF_SETONDRAGCOMPLETE = 1410;

	// Set drag handler script 
	public static final int CC_IF_SETONCLICKREPEAT = 1411;

	// Set mouse repeat handler script 
	public static final int CC_IF_SETONMOUSEREPEAT = 1412;

	// Set inventory transmit handler script 
	public static final int CC_IF_SETONINVTRANSMIT = 1414;

	// Set stat transmit handler script 
	public static final int CC_IF_SETONSTATTRANSMIT = 1415;

	// Set use handler script 
	public static final int CC_IF_SETONTARGETENTER = 1416;

	// Set scroll handler script 
	public static final int CC_IF_SETONSCROLLWHEEL = 1417;

	// Set message handler script 
	public static final int CC_IF_SETONCHATTRANSMIT = 1418;

	// Set key handler script 
	public static final int CC_IF_SETONKEY = 1419;

	// Set friend transmit handler script 
	public static final int CC_IF_SETONFRIENDTRANSMIT = 1420;

	// Set clan transmit handler script 
	public static final int CC_IF_SETONCLANTRANSMIT = 1421;

	// Set misc transmit handler script 
	public static final int CC_IF_SETONMISCTRANSMIT = 1422;

	// Set dialog abort handler script 
	public static final int CC_IF_SETONDIALOGABORT = 1423;

	// Set components open/close handler script 
	public static final int CC_IF_SETONSUBCHANGE = 1424;

	// Set stock transmit handler script 
	public static final int CC_IF_SETONSTOCKTRANSMIT = 1425;

	// Set minimap unlock handler script 
	public static final int CC_IF_SETONCAMFINISHED = 1426;

	// Set resize handler script 
	public static final int CC_IF_SETONRESIZE = 1427;

	// Set VarC transmit handler script 
	public static final int CC_IF_SETONVARCTRANSMIT = 1428;

	// Set VarC string transmit handler script 
	public static final int CC_IF_SETONVARCSTRTRANSMIT = 1429;

	// COMPONENT GETTERS (1500-1899)

	// Get component X position 
	public static final int CC_GETX = 1500;

	// Get component Y position 
	public static final int CC_GETY = 1501;

	// Get component width 
	public static final int CC_GETWIDTH = 1502;

	// Get component height 
	public static final int CC_GETHEIGHT = 1503;

	// Get component hidden state 
	public static final int CC_GETHIDE = 1504;

	// Get component layer/parent ID 
	public static final int CC_GETLAYER = 1505;

	// Get scroll X position 
	public static final int CC_GETSCROLLX = 1600;

	// Get scroll Y position 
	public static final int CC_GETSCROLLY = 1601;

	// Get component text 
	public static final int CC_GETTEXT = 1602;

	// Get scroll width 
	public static final int CC_GETSCROLLWIDTH = 1603;

	// Get scroll height 
	public static final int CC_GETSCROLLHEIGHT = 1604;

	// Get model zoom 
	public static final int CC_GETZOOM = 1605;

	// Get model X angle rotation 
	public static final int CC_GETMODELXANGLE = 1606;

	// Get model Y offset 
	public static final int CC_GETMODELYOFFSET = 1607;

	// Get model Y angle rotation 
	public static final int CC_GETMODELYANGLE = 1608;

	// Get transparency/alpha value 
	public static final int CC_GETTRANS = 1609;

	// Get model X offset 
	public static final int CC_GETMODELXOFFSET = 1610;

	// Get model Z offset 
	public static final int CC_GETMODELZOFFSET = 1611;

	// Get sprite graphic ID 
	public static final int CC_GETGRAPHIC = 1612;

	// Get inventory object ID at slot 
	public static final int CC_GETINVOBJECT = 1700;

	// Get inventory item count at slot 
	public static final int CC_GETINVCOUNT = 1701;

	// Get component created ID 
	public static final int CC_GETID = 1702;

	// Get component target mask 
	public static final int CC_GETTARGETMASK = 1800;

	// Get component option text 
	public static final int CC_GETOP = 1801;

	// Get component option base text 
	public static final int CC_GETOPBASE = 1802;

	// INTERFACE GETTERS (2500-2899)

	// Get interface component X position 
	public static final int IF_GETX = 2500;

	// Get interface component Y position 
	public static final int IF_GETY = 2501;

	// Get interface component width 
	public static final int IF_GETWIDTH = 2502;

	// Get interface component height 
	public static final int IF_GETHEIGHT = 2503;

	// Get interface component hidden state 
	public static final int IF_GETHIDE = 2504;

	// Get interface component layer 
	public static final int IF_GETLAYER = 2505;

	// Get interface scroll X 
	public static final int IF_GETSCROLLX = 2600;

	// Get interface scroll Y 
	public static final int IF_GETSCROLLY = 2601;

	// Get interface text 
	public static final int IF_GETTEXT = 2602;

	// Get interface scroll width 
	public static final int IF_GETSCROLLWIDTH = 2603;

	// Get interface scroll height 
	public static final int IF_GETSCROLLHEIGHT = 2604;

	// Get interface zoom 
	public static final int IF_GETZOOM = 2605;

	// Get interface model X angle 
	public static final int IF_GETMODELXANGLE = 2606;

	// Get interface model Y offset 
	public static final int IF_GETMODELYOFFSET = 2607;

	// Get interface model Y angle 
	public static final int IF_GETMODELYANGLE = 2608;

	// Get interface transparency 
	public static final int IF_GETTRANS = 2609;

	// Get interface model X offset 
	public static final int IF_GETMODELXOFFSET = 2610;

	// Get interface model Z offset 
	public static final int IF_GETMODELZOFFSET = 2611;

	// Get interface graphic 
	public static final int IF_GETGRAPHIC = 2612;

	// Get interface inventory object 
	public static final int IF_GETINVOBJECT = 2700;

	// Get interface inventory count 
	public static final int IF_GETINVCOUNT = 2701;

	// Check if interface has sub-interface 
	public static final int IF_HASSUB = 2702;

	// Get total sub-components count 
	public static final int IF_GETNEXTSUBID = 2703;

	public static final int IF_HASSUBMODAL = 2704;

	public static final int IF_HASSUBOVERLAY = 2705;

	// Get interface target mask 
	public static final int IF_GETTARGETMASK = 2800;

	// Get interface option text 
	public static final int IF_GETOP = 2801;

	// Get interface option base text 
	public static final int IF_GETOPBASE = 2802;

	// GAME COMMANDS (3100-3199)

	// Send chat message 
	public static final int MESSAGE = 3100;

	// Play player animation sequence 
	public static final int ANIM = 3101;

	// Close current interface 
	public static final int IF_CLOSE = 3103;

	// Send component trigger to server (int param) 
	public static final int IF_ON_COMPONENT = 3104;

	// Send component trigger to server (long param) 
	public static final int IF_ON_COMPONENT2 = 3105;

	// Send component trigger to server (string param) 
	public static final int IF_ON_COMPONENT3 = 3106;

	// Click player option (unknown exact use) 
	public static final int CLICK_PLAYER_OPTION = 3107;

	// Start component drag with component param 
	public static final int IF_DRAGPICKUP = 3108;

	// Start component drag with active param 
	public static final int STARTUSINGDRAG = 3109;

	// Send component trigger to server (short param) 
	public static final int IF_ON_COMPONENT4 = 3110;

	// SOUND/MUSIC (3200-3299)

	// Play synthesized sound effect 
	public static final int SOUND_SYNTH = 3200;

	// Play music track 
	public static final int SOUND_SONG = 3201;

	// Play jingle 
	public static final int SOUND_JINGLE = 3202;

	// GAME STATE GETTERS (3300-3399)

	// Get client loop counter/ticks 
	public static final int CLIENTCLOCK = 3300;

	// Get inventory item type at slot 
	public static final int INV_GETOBJ = 3301;

	// Get inventory item count at slot 
	public static final int INV_GETNUM = 3302;

	// Get total count of item type in inventory 
	public static final int INV_TOTAL = 3303;

	// Get inventory size (slot count) 
	public static final int INV_SIZE = 3304;

	// Get current skill level (boosted) 
	public static final int STAT = 3305;

	// Get base skill level 
	public static final int STAT_BASE = 3306;

	// Get skill XP 
	public static final int STAT_XP = 3307;

	// Get player coordinate (packed format: level_X_Z) 
	public static final int COORD = 3308;

	// Extract X coordinate from packed coord
	public static final int COORDX = 3309;

	// Extract Y (level/height) from packed coord 
	public static final int COORDY = 3310;

	// Extract Z coordinate from packed coord 
	public static final int COORDZ = 3311;

	// Check if members world 
	public static final int ISMEMBER = 3312;

	// Get inventory object (with offset variant) 
	public static final int INV_GETOBJ_OFFSET = 3313;

	// Get inventory count (with offset variant) 
	public static final int INV_GETNUM_OFFSET = 3314;

	// Get inventory total (with offset variant) 
	public static final int INV_TOTAL_OFFSET = 3315;

	// Get staff mod level (0-2: none/mod/admin) 
	public static final int MODLEVEL = 3316;

	// Get system update timer (seconds until reboot) 
	public static final int RUNTIMER = 3317;

	// Get world ID 
	public static final int WORLD = 3318;

	// Get run energy percentage 
	public static final int ENERGYSTAT = 3321;

	// Get weight carried 
	public static final int WEIGHT = 3322;

	// Check if player mod (returns 0 or 1) 
	public static final int PLAYERMOD = 3323;

	// Get player mod level (5-9 range) 
	public static final int PLAYERMODLEVEL = 3324;

	// Check if player is member 
	public static final int PLAYERMEMBER = 3325;

	// Get player combat level 
	public static final int COMBATLEVEL = 3326;

	// Get player gender (0=male, 1=female) 
	public static final int GENDER = 3327;

	// Check if underage player with no parental consent 
	public static final int PLAYERUNDERAGECHAT = 3328;

	// Check if quick chat only world 
	public static final int QUICKCHATWORLD = 3329;

	// Get inventory free space count 
	public static final int INV_FREESPACE = 3330;

	// Get total param value in inventory (non-banking) 
	public static final int INV_TOTALPARAM = 3331;

	// Get total param value in inventory (banking variant) 
	public static final int INV_TOTALPARAM_BANKING = 3332;

	// Unknown int getter 
	public static final int UNKNOWN_INT_GETTER = 3333;

	// Get client language ID 
	public static final int LANGUAGE = 3335;

	// Build packed coordinate from X, Z, level components 
	public static final int BUILDCOORD = 3336;

	// Get affiliate ID 
	public static final int AFFILIATE = 3337;

	// ENUM OPERATIONS (3400-3499)

	// Get enum value as string 
	public static final int ENUM = 3400;

	// Get enum output count with type check 
	public static final int ENUM_GETOUTPUTCOUNT = 3408;

	// Check if enum has output value 
	public static final int ENUM_HASOUTPUT = 3409;

	// Check if enum has output string 
	public static final int ENUM_HASOUTPUTSTRING = 3410;

	// Get enum size 
	public static final int ENUM_GETSIZE = 3411;

	// FRIEND/SOCIAL (3600-3699)

	// Get friend count (-2=loading, -1=connecting, 0+=count) 
	public static final int FRIENDCOUNT = 3600;

	// Get friend name by index 
	public static final int FRIENDGETNAME = 3601;

	// Get friend world number 
	public static final int FRIENDGETWORLD = 3602;

	// Get friend rank 
	public static final int FRIENDGETRANK = 3603;

	// Set friend rank 
	public static final int FRIENDSETRANK = 3604;

	// Add friend to list 
	public static final int FRIENDADD = 3605;

	// Remove friend from list 
	public static final int FRIENDDEL = 3606;

	// Add player to ignore list 
	public static final int IGNOREAD = 3607;

	// Remove player from ignore list 
	public static final int IGNOREDEL = 3608;

	// Check if username is in friend list 
	public static final int FRIENDTEST = 3609;

	// Get friend world name 
	public static final int FRIENDGETWORLDNAME = 3610;

	// Get clan chat name 
	public static final int CLANCHATGETNAME = 3611;

	// Get clan chat member count 
	public static final int CLANCHATGETSIZE = 3612;

	// Get clan member username 
	public static final int CLANCHATGETUSERNAME = 3613;

	// Get clan member world 
	public static final int CLANCHATGETUSERWORLD = 3614;

	// Get clan member rank 
	public static final int CLANCHATGETUSERRANK = 3615;

	// Get minimum rank to kick from clan 
	public static final int CLANCHATMINKICK = 3616;

	// Kick member from clan 
	public static final int CLANCHATKICK = 3617;

	// Get player's clan rank 
	public static final int CLANCHATRANK = 3618;

	// Join clan chat channel 
	public static final int CLANCHATJOIN = 3619;

	// Leave clan chat 
	public static final int CLANCHATLEAVE = 3620;

	// Get ignore list count 
	public static final int IGNORECOUNT = 3621;

	// Get ignored username 
	public static final int IGNOREGETNAME = 3622;

	// Check if username is ignored 
	public static final int IGNORETEST = 3623;

	// Check if self is in clan channel 
	public static final int CLANCHATISSELFINCHANNEL = 3624;

	// Get clan owner name 
	public static final int CLANCHATGETOWNER = 3625;

	// Get clan member world name 
	public static final int CLANCHATGETWORLDNAME = 3626;

	// Check if friend is in same game 
	public static final int FRIENDGETGAME = 3627;

	// Get friend index by username 
	public static final int FRIENDGETINDEX = 3628;

	// Get country ID 
	public static final int COUNTRY = 3629;

	// STOCK MARKET (3900-3999)

	// Get offer type (0=sell, 1=buy) 
	public static final int STOCKMARKET_GETOFFERTYPE = 3903;

	// Get offer item ID 
	public static final int STOCKMARKET_GETOFFERITEM = 3904;

	// Get offer price per item 
	public static final int STOCKMARKET_GETOFFERPRICE = 3905;

	// Get offer quantity 
	public static final int STOCKMARKET_GETOFFERCOUNT = 3906;

	// Get completed quantity 
	public static final int STOCKMARKET_GETOFFERCOMPLETEDCOUNT = 3907;

	// Get completed gold total 
	public static final int STOCKMARKET_GETOFFERCOMPLETEDGOLD = 3908;

	// Check if offer slot is empty 
	public static final int STOCKMARKET_ISOFFEREMPTY = 3910;

	// Check if offer is completing 
	public static final int STOCKMARKET_ISOFFERCOMPLETING = 3911;

	// Check if offer is finished 
	public static final int STOCKMARKET_ISOFFERFINISHED = 3912;

	// Check if offer is active 
	public static final int STOCKMARKET_ISOFFERACTIVE = 3913;

	// MATH OPERATIONS (4000-4099)

	// Add two integers 
	public static final int ADD = 4000;

	// Subtract integers 
	public static final int SUB = 4001;

	// Multiply integers 
	public static final int MULTIPLY = 4002;

	// Divide integers 
	public static final int DIVIDE = 4003;

	// Random integer [0, n) 
	public static final int RANDOM = 4004;

	// Random integer [0, n] inclusive 
	public static final int RANDOMINC = 4005;

	// Linear interpolation between values 
	public static final int INTERPOLATE = 4006;

	// Add percentage to value 
	public static final int ADDPERCENT = 4007;

	// Set bit in integer 
	public static final int SETBIT = 4008;

	// Clear bit in integer 
	public static final int CLEARBIT = 4009;

	// Test if bit is set 
	public static final int TESTBIT = 4010;

	// Modulo operation 
	public static final int MODULO = 4011;

	// Power operation (base^exponent) 
	public static final int POW = 4012;

	// Inverse power (nth root) 
	public static final int INVPOW = 4013;

	// Bitwise AND 
	public static final int BITWISE_AND = 4014;

	// Bitwise OR 
	public static final int BITWISE_OR = 4015;

	// Minimum of two values 
	public static final int MIN = 4016;

	// Maximum of two values 
	public static final int MAX = 4017;

	// Scale value (for percentage calculations) 
	public static final int SCALE = 4018;

	// STRING OPERATIONS (4100-4199)

	// Append number to string 
	public static final int APPEND_NUM = 4100;

	// Concatenate strings 
	public static final int APPEND = 4101;

	// Append signed number (+/-) 
	public static final int APPEND_SIGNNUM = 4102;

	// Convert string to lowercase 
	public static final int LOWERCASE = 4103;

	// Format date from day count 
	public static final int FROMDATE = 4104;

	// Select string by player gender 
	public static final int TEXT_GENDER = 4105;

	// Convert int to string 
	public static final int TOSTRING = 4106;

	// Compare two strings 
	public static final int COMPARE = 4107;

	// Calculate paragraph height in pixels 
	public static final int PARAHEIGHT = 4108;

	// Calculate paragraph width in pixels 
	public static final int PARAWIDTH = 4109;

	// Conditional string selection 
	public static final int TEXT_SWITCH = 4110;

	// Escape special characters in string 
	public static final int ESCAPE = 4111;

	// Append character to string 
	public static final int APPEND_CHAR = 4112;

	// Check if character is valid 
	public static final int CHAR_ISVALID = 4113;

	// Check character type (other) 
	public static final int CHAR_ISOTHER = 4114;

	// Check if character is letter 
	public static final int CHAR_ISLETTER = 4115;

	// Check if character is digit 
	public static final int CHAR_ISDIGIT = 4116;

	// Get string length 
	public static final int STRING_LENGTH = 4117;

	// Extract substring 
	public static final int SUBSTRING = 4118;

	// Remove XML/HTML tags from string 
	public static final int REMOVETAGS = 4119;

	// Find character in string 
	public static final int STRING_INDEXOF_CHAR = 4120;

	// Find substring in string 
	public static final int STRING_INDEXOF_STRING = 4121;

	// Convert character to lowercase 
	public static final int CHAR_TOLOWERCASE = 4122;

	// Convert character to uppercase 
	public static final int CHAR_TOUPPERCASE = 4123;

	// Format number with locale settings 
	public static final int FORMATNUMBER = 4124;

	// CONFIG GETTERS (4200-4499)

	// Get object/item name 
	public static final int OC_NAME = 4200;

	// Get object ground option text 
	public static final int OC_OP = 4201;

	// Get object inventory option text 
	public static final int OC_IOP = 4202;

	// Get object cost/value 
	public static final int OC_COST = 4203;

	// Check if object is stackable 
	public static final int OC_STACKABLE = 4204;

	// Get certificate link (cert -> item) 
	public static final int OC_CERT = 4205;

	// Get uncertificate link (item -> cert) 
	public static final int OC_UNCERT = 4206;

	// Check if object is members-only 
	public static final int OC_MEMBERS = 4207;

	// Get object param value 
	public static final int OC_PARAM = 4208;

	// Search objects by name 
	public static final int OC_FIND = 4210;

	// Get next object from search 
	public static final int OC_FINDNEXT = 4211;

	// Reset object search 
	public static final int OC_FINDRESET = 4212;

	// Get NPC param value 
	public static final int NC_PARAM = 4300;

	// Get location param value 
	public static final int LOC_PARAM = 4400;

	// Get struct param value 
	public static final int STRUCT_PARAM = 4500;

	// CHAT/QUICKCHAT (5000-5099)

	// Get public chat filter setting 
	public static final int CHAT_GETFILTER_PUBLIC = 5000;

	// Set all chat filters 
	public static final int CHAT_SETFILTER = 5001;

	// Report player for abuse 
	public static final int REPORTABUSE = 5002;

	// Get chat message by line 
	public static final int CHAT_GETHISTORY_BYTYPEANDLINE = 5003;

	// Get chat message type 
	public static final int CHAT_GETHISTORY_BYTYPE = 5004;

	// Get private chat filter 
	public static final int CHAT_GETFILTER_PRIVATE = 5005;

	// Send public chat message 
	public static final int CHAT_SENDPUBLIC = 5008;

	// Send private message 
	public static final int CHAT_SENDPRIVATE = 5009;

	// Get chat username 
	public static final int CHAT_GETHISTORY_USERNAME = 5010;

	// Get clan chat name from history 
	public static final int CHAT_GETHISTORY_CLAN = 5011;

	// Get quick chat phrase ID from history 
	public static final int CHAT_GETHISTORY_QUICKCHATID = 5012;

	// Get player username 
	public static final int GETPLAYERNAME = 5015;

	// Get trade chat filter 
	public static final int CHAT_GETFILTER_TRADE = 5016;

	// Get chat history length 
	public static final int CHAT_GETHISTORYLENGTH = 5017;

	// Get quick chat category name 
	public static final int QUICKCHAT_GETCATEGORYNAME = 5050;

	// Get category subcategory count 
	public static final int QUICKCHAT_GETCATEGORYSIZE = 5051;

	// Get subcategory ID 
	public static final int QUICKCHAT_GETSUBCATEGORY = 5052;

	// Get category phrase count 
	public static final int QUICKCHAT_GETCATEGORYPHRASESIZE = 5053;

	// Get category phrase ID 
	public static final int QUICKCHAT_GETCATEGORYPHRASE = 5054;

	// Get phrase text 
	public static final int QUICKCHAT_GETPHRASETRING = 5055;

	// Get dynamic command count 
	public static final int QUICKCHAT_GETPHRASEDYNAMICCOMMANDCOUNT = 5056;

	// Get dynamic command 
	public static final int QUICKCHAT_GETPHRASEDYNAMICCOMMAND = 5057;

	// Initialize quick chat phrase 
	public static final int QUICKCHAT_INIT = 5058;

	// Send quick chat public 
	public static final int QUICKCHAT_SENDPUBLIC = 5059;

	// Send quick chat private 
	public static final int QUICKCHAT_SENDPRIVATE = 5060;

	// Send quick chat to clan 
	public static final int QUICKCHAT_SENDCLAN = 5061;

	// Get subcategory shortcut 
	public static final int QUICKCHAT_GETSUBCATEGORYSHORTCUT = 5062;

	// Get phrase shortcut 
	public static final int QUICKCHAT_GETPHRASESHORTCUT = 5063;

	// Get subcategory index 
	public static final int QUICKCHAT_GETSUBCATEGORYINDEX = 5064;

	// Get phrase index 
	public static final int QUICKCHAT_GETPHRASEINDEX = 5065;

	// Get phrase dynamic count 
	public static final int QUICKCHAT_GETPHRASEDYNAMICCOUNT = 5066;

	// Get phrase dynamic value 
	public static final int QUICKCHAT_GETPHRASEDYNAMIC = 5067;

	// Set dynamic value (int) 
	public static final int QUICKCHAT_SETDYNAMIC = 5068;

	// Set dynamic value (alternate) 
	public static final int QUICKCHAT_SETDYNAMIC_ALT = 5069;

	// Get phrase param 
	public static final int QUICKCHAT_GETPHRASEPARAM = 5070;

	// Find quick chat phrases 
	public static final int QUICKCHAT_FINDPHRASE = 5071;

	// Get next phrase from search 
	public static final int QUICKCHAT_FINDNEXT = 5072;

	// Reset phrase search 
	public static final int QUICKCHAT_FINDRESET = 5073;

	// KEYBOARD (5100-5199)

	// Check if V key is held 
	public static final int KEY_HELD_V = 5100;

	// Check if R key is held 
	public static final int KEY_HELD_R = 5101;

	// Check if Q key is held 
	public static final int KEY_HELD_Q = 5102;

	// WORLD MAP (5200-5299)

	// Set world map zoom level 
	public static final int WORLDMAP_SETZOOM = 5200;

	// Get world map zoom level 
	public static final int WORLDMAP_GETZOOM = 5201;

	// Unknown world map operation 
	public static final int WORLDMAP_METHOD4444 = 5202;

	// Unknown world map operation 
	public static final int WORLDMAP_METHOD4656 = 5203;

	// Unknown world map operation 
	public static final int WORLDMAP_METHOD923 = 5204;

	// Unknown world map operation 
	public static final int WORLDMAP_METHOD1853 = 5205;

	// Get map name for coordinate 
	public static final int WORLDMAP_GETMAPNAME = 5206;

	// Get map display name 
	public static final int WORLDMAP_GETDISPLAYNAME = 5207;

	// Get current map position 
	public static final int WORLDMAP_GETCURRENTPOS = 5208;

	// Get display bounds 
	public static final int WORLDMAP_GETDISPLAYBOUNDS = 5209;

	// Get current map origin 
	public static final int WORLDMAP_GETCURRENTMAPORIGIN = 5210;

	// Get current map size 
	public static final int WORLDMAP_GETCURRENTMAPSIZE = 5211;

	// Get hovered label text 
	public static final int WORLDMAP_GETHOVEREDLABEL = 5212;

	// Get clicked label text 
	public static final int WORLDMAP_GETCLICKEDLABEL = 5213;

	// Jump to display coordinate 
	public static final int WORLDMAP_JUMPTODISPLAYCOORD = 5214;

	// Check if coord has map with group 
	public static final int WORLDMAP_COORDHASMAP = 5215;

	// Flash map element 
	public static final int WORLDMAP_FLASHELEMENT = 5216;

	// Check if element is flashing 
	public static final int WORLDMAP_ISELEMENTFLASHING = 5217;

	// Get default zoom for current map 
	public static final int WORLDMAP_GETCURRENTMAPDEFAULTZOOM = 5218;

	// Unknown world map operation 
	public static final int WORLDMAP_METHOD1149 = 5219;

	// Check if world map is loaded 
	public static final int WORLDMAP_ISLOADED = 5220;

	// DISPLAY/GRAPHICS (5300-5399)

	// Set fullscreen mode 
	public static final int SETFULLSCREEN = 5300;

	// Exit fullscreen mode 
	public static final int EXITFULLSCREEN = 5301;

	// Get available display mode count 
	public static final int GETDISPLAYMODECOUNT = 5302;

	// Get display mode dimensions 
	public static final int GETDISPLAYMODESIZE = 5303;

	// Get current display mode index 
	public static final int GETDISPLAYMODEINDEX = 5305;

	// Get window mode 
	public static final int GETWINDOWMODE = 5306;

	// Set window mode 
	public static final int SETWINDOWMODE = 5307;

	// Get default window mode 
	public static final int GETDEFAULTWINDOWMODE = 5308;

	// Set default window mode 
	public static final int SETDEFAULTWINDOWMODE = 5309;

	// MISC SYSTEM (5400-5499)

	// Unknown client script trigger 
	public static final int CLIENTSCRIPT_117 = 5400;

	// Set color override and clear caches 
	public static final int SETCOLOROVERRIDE = 5401;

	// Initialize camera spline 
	public static final int CAM_SPLINE_INIT = 5405;

	// Add spline control point 
	public static final int CAM_SPLINE_ADDPOINT = 5406;

	// Get spline length 
	public static final int CAM_SPLINE_LENGTH = 5407;

	// Quit to settings/logout 
	public static final int QUIT_TO_SETTINGS = 5411;

	// Get last login IP address 
	public static final int GETLASTLOGINADDRESS = 5419;

	// Check if running as applet 
	public static final int ISAPPLET = 5420;

	// Open URL in browser 
	public static final int OPENURL = 5421;

	// Set player display name 
	public static final int SETPLAYERNAME = 5422;

	// Print string to console 
	public static final int PRINTTOCONSOLE = 5423;

	// Set login UI sprite IDs 
	public static final int LOGIN_UI_SETSPRITES = 5424;

	// Clear login UI 
	public static final int LOGIN_UI_CLEAR = 5425;

	// Set anInt5794 (unknown purpose) 
	public static final int SET_ANINT5794 = 5426;

	// Set minimap ints (unknown purpose) 
	public static final int MINIMAP_SETINTS = 5427;

	// CAMERA (5500-5599)

	// Move camera to position 
	public static final int CAM_MOVETO = 5500;

	// Point camera at coordinate 
	public static final int CAM_LOOKAT = 5501;

	// Move camera along spline 
	public static final int CAM_MOVETO_SPLINE = 5502;

	// Reset camera effects 
	public static final int CAM_RESET = 5503;

	// Set manual camera angles 
	public static final int CAM_SETANGLE = 5504;

	// Get camera pitch 
	public static final int CAM_GETPITCH = 5505;

	// Get camera yaw 
	public static final int CAM_GETYAW = 5506;

	// LOGIN/ACCOUNT (5600-5699)

	// Login with credentials 
	public static final int LOGIN = 5600;

	// Advance login step 
	public static final int LOGIN_ADVANCE = 5601;

	// Abort login 
	public static final int LOGIN_ABORT = 5602;

	// Check account creation info 
	public static final int CREATEACCOUNT_CHECKINFO = 5603;

	// Check username availability 
	public static final int CREATEACCOUNT_CHECKNAME = 5604;

	// Create account 
	public static final int CREATEACCOUNT_CREATE = 5605;

	// Abort account creation 
	public static final int CREATEACCOUNT_ABORT = 5606;

	// Get login reply code 
	public static final int LOGIN_GETREPLY = 5607;

	// Get hop time 
	public static final int LOGIN_GETHOPTIME = 5608;

	// Get create account reply 
	public static final int CREATEACCOUNT_GETREPLY = 5609;

	// Get suggested usernames 
	public static final int CREATEACCOUNT_GETSUGGESTEDNAMES = 5610;

	// Get disallow result 
	public static final int LOGIN_GETDISALLOWRESULT = 5611;

	// PREFERENCES/SETTINGS (6000-6199)

	// Set display brightness (1-4) 
	public static final int SETBRIGHTNESS = 6001;

	// Set low memory mode 
	public static final int SETLOWMEM = 6002;

	// Set roofs visible 
	public static final int SETROOFSVISIBLE = 6003;

	// Set ground decorations visible 
	public static final int SETGROUNDDECOR = 6005;

	// Set high detail textures 
	public static final int SETHIGHTEXTURES = 6006;

	// Set many idle animations 
	public static final int SETMANYIDLEANIMS = 6007;

	// Set flickering effects 
	public static final int SETFLICKERINGEFFECTS = 6008;

	// Set many ground textures 
	public static final int SETMANYGROUNDTEXTURES = 6009;

	// Set character shadows 
	public static final int SETCHARACTERSHADOWS = 6010;

	// Set scenery shadow type 
	public static final int SETSCENERYSHADOWS = 6011;

	// Set high detail lighting 
	public static final int SETHIGHLIGHTING = 6012;

	// Set high water detail 
	public static final int SETHIGHWATER = 6014;

	// Set fog enabled 
	public static final int SETFOG = 6015;

	// Set anti-aliasing mode 
	public static final int SETANTIALIASING = 6016;

	// Set stereo sound 
	public static final int SETSTEREO = 6017;

	// Set sound effect volume 
	public static final int SETSOUNDVOLUME = 6018;

	// Set music volume 
	public static final int SETMUSICVOLUME = 6019;

	// Set ambient sounds volume 
	public static final int SETAMBIENTSOUNDSVOLUME = 6020;

	// Set never remove roofs 
	public static final int SETNEVERREMOVINGROOFS = 6021;

	// Set particle level 
	public static final int SETPARTICLES = 6023;

	// Set window mode preference 
	public static final int SETWINDOWMODE_PREF = 6024;

	// Set custom cursors enabled 
	public static final int SETCURSORS = 6028;

	// Get brightness setting 
	public static final int GETBRIGHTNESS = 6101;

	// Get low memory mode 
	public static final int GETLOWMEM = 6102;

	// Get roofs visible 
	public static final int GETROOFSVISIBLE = 6103;

	// Get ground decorations setting 
	public static final int GETGROUNDDECOR = 6105;

	// Get high textures setting 
	public static final int GETHIGHTEXTURES = 6106;

	// Get many idle anims setting 
	public static final int GETMANYIDLEANIMS = 6107;

	// Get flickering effects setting 
	public static final int GETFLICKERINGEFFECTS = 6108;

	// Get many ground textures 
	public static final int GETMANYGROUNDTEXTURES = 6109;

	// Get character shadows setting 
	public static final int GETCHARACTERSHADOWS = 6110;

	// Get scenery shadows setting 
	public static final int GETSCENERYSHADOWS = 6111;

	// Get high lighting setting 
	public static final int GETHIGHLIGHTING = 6112;

	// Get high water setting 
	public static final int GETHIGHWATER = 6114;

	// Get fog setting 
	public static final int GETFOG = 6115;

	// Get anti-aliasing setting 
	public static final int GETANTIALIASING = 6116;

	// Get stereo setting 
	public static final int GETSTEREO = 6117;

	// Get sound volume 
	public static final int GETSOUNDVOLUME = 6118;

	// Get music volume 
	public static final int GETMUSICVOLUME = 6119;

	// Get ambient sounds volume 
	public static final int GETAMBIENTSOUNDSVOLUME = 6120;

	// Check if AA is available 
	public static final int GETANTIALIASINGAVAILABLE = 6121;

	// Get particle setting 
	public static final int GETPARTICLES = 6123;

	// Get window mode preference 
	public static final int GETWINDOWMODE_PREF = 6124;

	// Get cursors enabled 
	public static final int GETCURSORS = 6128;

	// SCREEN/RESOLUTION (6200-6299)

	// Sets zoom scale min/max
	public static final int CAM_SETZOOMSCALE = 6200;

	// Sets distance scale min/max
	public static final int CAM_SETDISTSCALE = 6201;

	// Sets all 4 constraint limits
	public static final int CAM_SETCONSTRAINTS = 6202;

	// Gets calculated viewport dimensions
	public static final int CAM_GETVIEWPORTSIZE = 6203;

	// Gets distance scale range
	public static final int CAM_GETDISTSCALE = 6204;

	// Gets zoom scale range
	public static final int CAM_GETZOOMSCALE = 6205;

	// DATE/TIME (6300-6399)

	// Get current time in minutes since midnight 
	public static final int CALC_TIME_MINUTES = 6300;

	// Get current date in days since Jan 1, 2002 
	public static final int CALC_TIME_DAYS = 6301;

	// Convert date components to day count 
	public static final int CALC_DATE = 6302;

	// Get current year 
	public static final int CALC_YEAR = 6303;

	// Check if year is leap year 
	public static final int CALC_ISLEAPYEAR = 6304;

	// VIDEO ADS (6400-6499)

	// Show video ad (disabled, returns 0) 
	public static final int SHOWVIDEOAD = 6405;

	// Check if showing video ad (disabled, returns 0) 
	public static final int ISSHOWINGVIDEOAD = 6406;

	// WORLD LIST (6500-6599)

	// Fetch world list from server 
	public static final int WORLDLIST_FETCH = 6500;

	// Get first world info 
	public static final int WORLDLIST_START = 6501;

	// Get next world info 
	public static final int WORLDLIST_NEXT = 6502;

	// Hop to world 
	public static final int WORLDLIST_HOPWORLD = 6503;

	// Set last world ID 
	public static final int WORLDLIST_SETLAST = 6504;

	// Get last world ID 
	public static final int WORLDLIST_GETLAST = 6505;

	// Get world info by ID 
	public static final int WORLDLIST_GETBYID = 6506;

	// Sort world list 
	public static final int WORLDLIST_SORT = 6507;

	// UNKNOWN (6600-6699)

	// Set boolean 63 preference
	public static final int SET_KEYBOARDCAMERA = 6600;

	// Get boolean 63 preference
	public static final int GET_KEYBOARDCAMERA = 6601;

}

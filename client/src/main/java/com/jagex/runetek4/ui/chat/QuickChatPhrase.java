package com.jagex.runetek4.ui.chat;

import com.jagex.runetek4.config.types.quickchat.QuickChatPhraseType;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

// QuickChatPhrase
@OriginalClass("client!bd")
public final class QuickChatPhrase {

	@OriginalMember(owner = "client!bd", name = "c", descriptor = "I")
	public int id;

	@OriginalMember(owner = "client!bd", name = "f", descriptor = "Lclient!cb;")
	public QuickChatPhraseType type;

	@OriginalMember(owner = "client!bd", name = "h", descriptor = "[I")
	public int[] values;

}

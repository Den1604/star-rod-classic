/* Generated By:JJTree&JavaCC: Do not edit this line. InlineParserTokenManager.java */
package game.shared.struct.script.inline.generated;

/** Token Manager. */
public class InlineParserTokenManager implements InlineParserConstants
{

	/** Debug output. */
	public java.io.PrintStream debugStream = System.out;

	/** Set debug output. */
	public void setDebugStream(java.io.PrintStream ds)
	{
		debugStream = ds;
	}

	private int jjStopAtPos(int pos, int kind)
	{
		jjmatchedKind = kind;
		jjmatchedPos = pos;
		return pos + 1;
	}

	private int jjMoveStringLiteralDfa0_0()
	{
		switch (curChar) {
			case 9:
				jjmatchedKind = 2;
				return jjMoveNfa_0(1, 0);
			case 10:
				jjmatchedKind = 4;
				return jjMoveNfa_0(1, 0);
			case 13:
				jjmatchedKind = 3;
				return jjMoveNfa_0(1, 0);
			case 32:
				jjmatchedKind = 1;
				return jjMoveNfa_0(1, 0);
			case 38:
				jjmatchedKind = 22;
				return jjMoveNfa_0(1, 0);
			case 40:
				jjmatchedKind = 28;
				return jjMoveNfa_0(1, 0);
			case 41:
				jjmatchedKind = 29;
				return jjMoveNfa_0(1, 0);
			case 42:
				jjmatchedKind = 25;
				return jjMoveNfa_0(1, 0);
			case 43:
				jjmatchedKind = 23;
				return jjMoveNfa_0(1, 0);
			case 44:
				jjmatchedKind = 30;
				return jjMoveNfa_0(1, 0);
			case 45:
				jjmatchedKind = 24;
				return jjMoveNfa_0(1, 0);
			case 47:
				jjmatchedKind = 26;
				return jjMoveNfa_0(1, 0);
			case 64:
				jjmatchedKind = 27;
				return jjMoveNfa_0(1, 0);
			case 70:
				return jjMoveStringLiteralDfa1_0(0x40000L);
			case 73:
				return jjMoveStringLiteralDfa1_0(0x20000L);
			case 77:
				return jjMoveStringLiteralDfa1_0(0x80000L);
			case 83:
				return jjMoveStringLiteralDfa1_0(0x100000L);
			case 91:
				jjmatchedKind = 31;
				return jjMoveNfa_0(1, 0);
			case 93:
				jjmatchedKind = 32;
				return jjMoveNfa_0(1, 0);
			case 102:
				return jjMoveStringLiteralDfa1_0(0x40000L);
			case 105:
				return jjMoveStringLiteralDfa1_0(0x20000L);
			case 109:
				return jjMoveStringLiteralDfa1_0(0x80000L);
			case 115:
				return jjMoveStringLiteralDfa1_0(0x100000L);
			case 124:
				jjmatchedKind = 21;
				return jjMoveNfa_0(1, 0);
			default:
				return jjMoveNfa_0(1, 0);
		}
	}

	private int jjMoveStringLiteralDfa1_0(long active0)
	{
		try {
			curChar = input_stream.readChar();
		}
		catch (java.io.IOException e) {
			return jjMoveNfa_0(1, 0);
		}
		switch (curChar) {
			case 76:
				return jjMoveStringLiteralDfa2_0(active0, 0x40000L);
			case 78:
				return jjMoveStringLiteralDfa2_0(active0, 0x20000L);
			case 79:
				return jjMoveStringLiteralDfa2_0(active0, 0x80000L);
			case 81:
				return jjMoveStringLiteralDfa2_0(active0, 0x100000L);
			case 108:
				return jjMoveStringLiteralDfa2_0(active0, 0x40000L);
			case 110:
				return jjMoveStringLiteralDfa2_0(active0, 0x20000L);
			case 111:
				return jjMoveStringLiteralDfa2_0(active0, 0x80000L);
			case 113:
				return jjMoveStringLiteralDfa2_0(active0, 0x100000L);
			default:
				break;
		}
		return jjMoveNfa_0(1, 1);
	}

	private int jjMoveStringLiteralDfa2_0(long old0, long active0)
	{
		if (((active0 &= old0)) == 0L)
			return jjMoveNfa_0(1, 1);
		try {
			curChar = input_stream.readChar();
		}
		catch (java.io.IOException e) {
			return jjMoveNfa_0(1, 1);
		}
		switch (curChar) {
			case 68:
				if ((active0 & 0x80000L) != 0L) {
					jjmatchedKind = 19;
					jjmatchedPos = 2;
				}
				break;
			case 79:
				return jjMoveStringLiteralDfa3_0(active0, 0x40000L);
			case 84:
				if ((active0 & 0x20000L) != 0L) {
					jjmatchedKind = 17;
					jjmatchedPos = 2;
				}
				break;
			case 85:
				return jjMoveStringLiteralDfa3_0(active0, 0x100000L);
			case 100:
				if ((active0 & 0x80000L) != 0L) {
					jjmatchedKind = 19;
					jjmatchedPos = 2;
				}
				break;
			case 111:
				return jjMoveStringLiteralDfa3_0(active0, 0x40000L);
			case 116:
				if ((active0 & 0x20000L) != 0L) {
					jjmatchedKind = 17;
					jjmatchedPos = 2;
				}
				break;
			case 117:
				return jjMoveStringLiteralDfa3_0(active0, 0x100000L);
			default:
				break;
		}
		return jjMoveNfa_0(1, 2);
	}

	private int jjMoveStringLiteralDfa3_0(long old0, long active0)
	{
		if (((active0 &= old0)) == 0L)
			return jjMoveNfa_0(1, 2);
		try {
			curChar = input_stream.readChar();
		}
		catch (java.io.IOException e) {
			return jjMoveNfa_0(1, 2);
		}
		switch (curChar) {
			case 65:
				return jjMoveStringLiteralDfa4_0(active0, 0x140000L);
			case 97:
				return jjMoveStringLiteralDfa4_0(active0, 0x140000L);
			default:
				break;
		}
		return jjMoveNfa_0(1, 3);
	}

	private int jjMoveStringLiteralDfa4_0(long old0, long active0)
	{
		if (((active0 &= old0)) == 0L)
			return jjMoveNfa_0(1, 3);
		try {
			curChar = input_stream.readChar();
		}
		catch (java.io.IOException e) {
			return jjMoveNfa_0(1, 3);
		}
		switch (curChar) {
			case 82:
				return jjMoveStringLiteralDfa5_0(active0, 0x100000L);
			case 84:
				if ((active0 & 0x40000L) != 0L) {
					jjmatchedKind = 18;
					jjmatchedPos = 4;
				}
				break;
			case 114:
				return jjMoveStringLiteralDfa5_0(active0, 0x100000L);
			case 116:
				if ((active0 & 0x40000L) != 0L) {
					jjmatchedKind = 18;
					jjmatchedPos = 4;
				}
				break;
			default:
				break;
		}
		return jjMoveNfa_0(1, 4);
	}

	private int jjMoveStringLiteralDfa5_0(long old0, long active0)
	{
		if (((active0 &= old0)) == 0L)
			return jjMoveNfa_0(1, 4);
		try {
			curChar = input_stream.readChar();
		}
		catch (java.io.IOException e) {
			return jjMoveNfa_0(1, 4);
		}
		switch (curChar) {
			case 69:
				if ((active0 & 0x100000L) != 0L) {
					jjmatchedKind = 20;
					jjmatchedPos = 5;
				}
				break;
			case 101:
				if ((active0 & 0x100000L) != 0L) {
					jjmatchedKind = 20;
					jjmatchedPos = 5;
				}
				break;
			default:
				break;
		}
		return jjMoveNfa_0(1, 5);
	}

	private int jjMoveNfa_0(int startState, int curPos)
	{
		int strKind = jjmatchedKind;
		int strPos = jjmatchedPos;
		int seenUpto;
		input_stream.backup(seenUpto = curPos + 1);
		try {
			curChar = input_stream.readChar();
		}
		catch (java.io.IOException e) {
			throw new Error("Internal Error");
		}
		curPos = 0;
		int startsAt = 0;
		jjnewStateCnt = 24;
		int i = 1;
		jjstateSet[0] = startState;
		int kind = 0x7fffffff;
		for (;;) {
			if (++jjround == 0x7fffffff)
				ReInitRounds();
			if (curChar < 64) {
				long l = 1L << curChar;
				do {
					switch (jjstateSet[--i]) {
						case 1:
							if ((0x3ff000000000000L & l) != 0L)
								jjCheckNAddStates(0, 3);
							else if (curChar == 36)
								jjstateSet[jjnewStateCnt++] = 13;
							else if (curChar == 42)
								jjstateSet[jjnewStateCnt++] = 5;
							else if (curChar == 46)
								jjstateSet[jjnewStateCnt++] = 2;
							if ((0x3ff000000000000L & l) != 0L) {
								if (kind > 5)
									kind = 5;
								jjCheckNAdd(0);
							}
							break;
						case 0:
							if ((0x3ff000000000000L & l) == 0L)
								break;
							if (kind > 5)
								kind = 5;
							jjCheckNAdd(0);
							break;
						case 3:
							if ((0x87ff000000000000L & l) == 0L)
								break;
							if (kind > 10)
								kind = 10;
							jjstateSet[jjnewStateCnt++] = 3;
							break;
						case 4:
							if (curChar == 42)
								jjstateSet[jjnewStateCnt++] = 5;
							break;
						case 6:
							if ((0x87ff000000000000L & l) == 0L)
								break;
							if (kind > 11)
								kind = 11;
							jjAddStates(4, 5);
							break;
						case 8:
							if ((0x3ff000000000000L & l) != 0L)
								jjAddStates(6, 7);
							break;
						case 9:
							if (curChar == 39)
								jjCheckNAdd(10);
							break;
						case 11:
							if ((0x3ff000000000000L & l) != 0L)
								jjCheckNAddTwoStates(11, 10);
							break;
						case 12:
							if (curChar == 36)
								jjstateSet[jjnewStateCnt++] = 13;
							break;
						case 14:
							if ((0x87ff000000000000L & l) == 0L)
								break;
							if (kind > 12)
								kind = 12;
							jjstateSet[jjnewStateCnt++] = 14;
							break;
						case 15:
							if ((0x3ff000000000000L & l) != 0L)
								jjCheckNAddStates(0, 3);
							break;
						case 16:
							if ((0x3ff000000000000L & l) != 0L)
								jjCheckNAddTwoStates(16, 17);
							break;
						case 17:
							if (curChar == 39 && kind > 5)
								kind = 5;
							break;
						case 18:
							if ((0x3ff000000000000L & l) != 0L)
								jjCheckNAddTwoStates(18, 19);
							break;
						case 19:
							if (curChar != 46)
								break;
							if (kind > 8)
								kind = 8;
							jjCheckNAddTwoStates(20, 21);
							break;
						case 20:
							if ((0x3ff000000000000L & l) == 0L)
								break;
							if (kind > 8)
								kind = 8;
							jjCheckNAddTwoStates(20, 21);
							break;
						case 22:
							if ((0x280000000000L & l) != 0L)
								jjCheckNAdd(23);
							break;
						case 23:
							if ((0x3ff000000000000L & l) == 0L)
								break;
							if (kind > 8)
								kind = 8;
							jjCheckNAdd(23);
							break;
						default:
							break;
					}
				}
				while (i != startsAt);
			}
			else if (curChar < 128) {
				long l = 1L << (curChar & 077);
				do {
					switch (jjstateSet[--i]) {
						case 1:
						case 0:
							if ((0x7e0000007eL & l) == 0L)
								break;
							if (kind > 5)
								kind = 5;
							jjCheckNAdd(0);
							break;
						case 2:
						case 3:
							if ((0x7fffffe87fffffeL & l) == 0L)
								break;
							if (kind > 10)
								kind = 10;
							jjCheckNAdd(3);
							break;
						case 5:
						case 6:
							if ((0x7fffffe87fffffeL & l) == 0L)
								break;
							if (kind > 11)
								kind = 11;
							jjCheckNAddTwoStates(6, 7);
							break;
						case 7:
							if (curChar == 91)
								jjCheckNAddTwoStates(8, 11);
							break;
						case 9:
							if (curChar == 96)
								jjCheckNAdd(10);
							break;
						case 10:
							if (curChar == 93)
								kind = 11;
							break;
						case 11:
							if ((0x7e0000007eL & l) != 0L)
								jjCheckNAddTwoStates(11, 10);
							break;
						case 13:
						case 14:
							if ((0x7fffffe87fffffeL & l) == 0L)
								break;
							if (kind > 12)
								kind = 12;
							jjCheckNAdd(14);
							break;
						case 17:
							if (curChar == 96 && kind > 5)
								kind = 5;
							break;
						case 21:
							if ((0x2000000020L & l) != 0L)
								jjAddStates(8, 9);
							break;
						default:
							break;
					}
				}
				while (i != startsAt);
			}
			else {
				int i2 = (curChar & 0xff) >> 6;
				long l2 = 1L << (curChar & 077);
				do {
					switch (jjstateSet[--i]) {
						default:
							break;
					}
				}
				while (i != startsAt);
			}
			if (kind != 0x7fffffff) {
				jjmatchedKind = kind;
				jjmatchedPos = curPos;
				kind = 0x7fffffff;
			}
			++curPos;
			if ((i = jjnewStateCnt) == (startsAt = 24 - (jjnewStateCnt = startsAt)))
				break;
			try {
				curChar = input_stream.readChar();
			}
			catch (java.io.IOException e) {
				break;
			}
		}
		if (jjmatchedPos > strPos)
			return curPos;

		int toRet = Math.max(curPos, seenUpto);

		if (curPos < toRet)
			for (i = toRet - Math.min(curPos, seenUpto); i-- > 0;)
				try {
					curChar = input_stream.readChar();
				}
				catch (java.io.IOException e) {
					throw new Error("Internal Error : Please send a bug report.");
				}

		if (jjmatchedPos < strPos) {
			jjmatchedKind = strKind;
			jjmatchedPos = strPos;
		}
		else if (jjmatchedPos == strPos && jjmatchedKind > strKind)
			jjmatchedKind = strKind;

		return toRet;
	}

	static final int[] jjnextStates = {
			16, 17, 18, 19, 6, 7, 8, 9, 22, 23,
	};

	/** Token literal values. */
	public static final String[] jjstrLiteralImages = {
			"", null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, "\174", "\46", "\53", "\55", "\52",
			"\57", "\100", "\50", "\51", "\54", "\133", "\135", };

	/** Lexer state names. */
	public static final String[] lexStateNames = {
			"DEFAULT",
	};
	static final long[] jjtoToken = {
			0x1fffe1d21L,
	};
	static final long[] jjtoSkip = {
			0x1eL,
	};
	protected SimpleCharStream input_stream;
	private final int[] jjrounds = new int[24];
	private final int[] jjstateSet = new int[48];
	protected char curChar;

	/** Constructor. */
	public InlineParserTokenManager(SimpleCharStream stream)
	{
		if (SimpleCharStream.staticFlag)
			throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
		input_stream = stream;
	}

	/** Constructor. */
	public InlineParserTokenManager(SimpleCharStream stream, int lexState)
	{
		this(stream);
		SwitchTo(lexState);
	}

	/** Reinitialise parser. */
	public void ReInit(SimpleCharStream stream)
	{
		jjmatchedPos = jjnewStateCnt = 0;
		curLexState = defaultLexState;
		input_stream = stream;
		ReInitRounds();
	}

	private void ReInitRounds()
	{
		int i;
		jjround = 0x80000001;
		for (i = 24; i-- > 0;)
			jjrounds[i] = 0x80000000;
	}

	/** Reinitialise parser. */
	public void ReInit(SimpleCharStream stream, int lexState)
	{
		ReInit(stream);
		SwitchTo(lexState);
	}

	/** Switch to specified lex state. */
	public void SwitchTo(int lexState)
	{
		if (lexState >= 1 || lexState < 0)
			throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
		else
			curLexState = lexState;
	}

	protected Token jjFillToken()
	{
		final Token t;
		final String curTokenImage;
		final int beginLine;
		final int endLine;
		final int beginColumn;
		final int endColumn;
		String im = jjstrLiteralImages[jjmatchedKind];
		curTokenImage = (im == null) ? input_stream.GetImage() : im;
		beginLine = input_stream.getBeginLine();
		beginColumn = input_stream.getBeginColumn();
		endLine = input_stream.getEndLine();
		endColumn = input_stream.getEndColumn();
		t = Token.newToken(jjmatchedKind, curTokenImage);

		t.beginLine = beginLine;
		t.endLine = endLine;
		t.beginColumn = beginColumn;
		t.endColumn = endColumn;

		return t;
	}

	int curLexState = 0;
	int defaultLexState = 0;
	int jjnewStateCnt;
	int jjround;
	int jjmatchedPos;
	int jjmatchedKind;

	/** Get the next Token. */
	public Token getNextToken()
	{
		Token matchedToken;
		int curPos = 0;

		EOFLoop:
		for (;;) {
			try {
				curChar = input_stream.BeginToken();
			}
			catch (java.io.IOException e) {
				jjmatchedKind = 0;
				matchedToken = jjFillToken();
				return matchedToken;
			}

			jjmatchedKind = 0x7fffffff;
			jjmatchedPos = 0;
			curPos = jjMoveStringLiteralDfa0_0();
			if (jjmatchedKind != 0x7fffffff) {
				if (jjmatchedPos + 1 < curPos)
					input_stream.backup(curPos - jjmatchedPos - 1);
				if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L) {
					matchedToken = jjFillToken();
					return matchedToken;
				}
				else {
					continue EOFLoop;
				}
			}
			int error_line = input_stream.getEndLine();
			int error_column = input_stream.getEndColumn();
			String error_after = null;
			boolean EOFSeen = false;
			try {
				input_stream.readChar();
				input_stream.backup(1);
			}
			catch (java.io.IOException e1) {
				EOFSeen = true;
				error_after = curPos <= 1 ? "" : input_stream.GetImage();
				if (curChar == '\n' || curChar == '\r') {
					error_line++;
					error_column = 0;
				}
				else
					error_column++;
			}
			if (!EOFSeen) {
				input_stream.backup(1);
				error_after = curPos <= 1 ? "" : input_stream.GetImage();
			}
			throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
		}
	}

	private void jjCheckNAdd(int state)
	{
		if (jjrounds[state] != jjround) {
			jjstateSet[jjnewStateCnt++] = state;
			jjrounds[state] = jjround;
		}
	}

	private void jjAddStates(int start, int end)
	{
		do {
			jjstateSet[jjnewStateCnt++] = jjnextStates[start];
		}
		while (start++ != end);
	}

	private void jjCheckNAddTwoStates(int state1, int state2)
	{
		jjCheckNAdd(state1);
		jjCheckNAdd(state2);
	}

	private void jjCheckNAddStates(int start, int end)
	{
		do {
			jjCheckNAdd(jjnextStates[start]);
		}
		while (start++ != end);
	}

}

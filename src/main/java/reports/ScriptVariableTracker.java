package reports;

import static game.shared.struct.script.ScriptVariable.GameByte;
import static game.shared.struct.script.ScriptVariable.GameFlag;

import util.Logger;

public class ScriptVariableTracker
{
	private static boolean[] foundBytes = new boolean[GameByte.getMaxIndex()];
	private static boolean[] foundFlags = new boolean[GameFlag.getMaxIndex()];

	private static boolean[] machiBytes = new boolean[GameByte.getMaxIndex()];
	private static boolean[] machiFlags = new boolean[GameFlag.getMaxIndex()];

	private static final String machiByteString = "111000000000" + // Byte_EVT
		"111111000000000" + // Byte_KMR
		"11111111111111111111000000000" + // Byte_MAC
		"11000000000" + // Byte_TIK
		"1000000000" + // Byte_KGR
		"1111000000000" + // Byte_KKJ
		"1000000000" + // Byte_HOS
		"111000000000" + // Byte_NOK
		"1000000000" + // Byte_TRD
		"111110000000" + // Byte_IWA
		"111000000000" + // Byte_DRO
		"1000000000" + // Byte_SBK
		"111111000000000" + // Byte_ISK
		"11000000000" + // Byte_MIM
		"11000000000" + // Byte_OBK
		"1000000000" + // Byte_ARN
		"11000000000" + // Byte_DGB
		"111111000000000" + // Byte_OMO
		"1000000000" + // Byte_JAN
		"1000000000" + // Byte_KZN
		"111111000000000" + // Byte_FLO
		"1111111110000000000" + // Byte_SAM
		"11111000000000" + // Byte_PRA
		"111111111000000000" + // Byte_KPA
		"1000000000" + // Byte_OSR
		"11000000000" + // Byte_END
		"1000000000" + // Byte_BAT
		"1111111111000000000" + // Byte_NPC
		"1111111111111111111111111111111000000000" + // Byte_BTL
		"1000000000" + // Byte_FBTL
		"1000000000" + // Byte_DOKAN
		"11000000000" + // Byte_MAP
		"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

	private static final String machiFlagString = "111111111110000000000000000000" + // Flag_EVT
		"1111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000" + // Flag_KMR
		"111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000"
		+ // Flag_MAC
		"11111111111111111111111111111111111111111111111111110000000000000000000" + // Flag_TIK
		"10000000000000000000" + // Flag_KGR
		"1111111111111111111111111111111110000000000000000000" + // Flag_KKJ
		"11111111111111110000000000000000000" + // Flag_HOS
		"111111111111111111111111111111111111111111111111111110000000000000000000" + // Flag_NOK
		"111111111111111111111111111111000000000000000000" + // Flag_TRD
		"111111111111111111111111111111111111110000000000000000000000" + // Flag_IWA
		"111111111111111110000000000000000000" + // Flag_DRO
		"1111111111111111111111111111111111111111111111111111111111111111111110000000000000000000" + // Flag_SBK
		"111111111111111111111111111111111111110000000000000000" + // Flag_ISK
		"11111111111110000000000000000000" + // Flag_MIM
		"111111111111111111111111111000000000000000000" + // Flag_OBK
		"111111111111111111111000000000000000000" + // Flag_ARN
		"1111111111111111111111111111111110000000000000000000000000" + // Flag_DGB
		"111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000" + // Flag_OMO
		"111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000" + // Flag_JAN
		"111111111111111111111111111111111000000000000000000000" + // Flag_KZN
		"111111111111111111111111111111111111111111111111111100000000000000000" + // Flag_FLO
		"11111111111111111111111111111111111111110000000000000000000" + // Flag_SAM
		"1111111111111111111111111111111100000000000000000000000" + // Flag_PRA
		"11111111111111111111111111111111111111111111111100000000000000000000000000000" + // Flag_KPA
		"110000000000000000000" + // Flag_END
		"110000000000000000000" + // Flag_OSR
		"1111111111111111111111000000000000000000" + // Flag_BAT
		"1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000" + // Flag_NPC
		"1111111100000000000000000" + // Flag_BTL
		"11111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000" + // Flag_FBTL
		"11111111110000000000000000000" + // Flag_DOKAN
		"111111111111111111111111111111111110000000000000000000" + // Flag_MAP
		"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

	static {
		for (int i = 0; i < machiBytes.length; i++)
			machiBytes[i] = machiByteString.charAt(i) == '1';

		for (int i = 0; i < machiFlags.length; i++)
			machiFlags[i] = machiFlagString.charAt(i) == '1';
	}

	public static void foundByte(int i)
	{
		foundBytes[i] = true;

		if (!machiBytes[i])
			Logger.logWarning("BYTE CONFLICTS WITH MACHI: " + i);
	}

	public static void foundFlag(int i)
	{
		foundFlags[i] = true;

		if (!machiFlags[i])
			Logger.logWarning("FLAG CONFLICTS WITH MACHI: " + i);
	}

}
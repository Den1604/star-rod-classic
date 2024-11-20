package game.map.struct.shop;

import java.io.PrintWriter;
import java.nio.ByteBuffer;

import game.shared.BaseStruct;
import game.shared.ProjectDatabase;
import game.shared.decoder.BaseDataDecoder;
import game.shared.decoder.Pointer;

public class ShopInventory extends BaseStruct
{
	public static final ShopInventory instance = new ShopInventory();

	private ShopInventory()
	{}

	@Override
	public void scan(BaseDataDecoder decoder, Pointer ptr, ByteBuffer fileBuffer)
	{
		while (fileBuffer.getInt() != 0) {
			fileBuffer.getInt();
			fileBuffer.getInt();
		}

		fileBuffer.getInt();
		fileBuffer.getInt();
	}

	@Override
	public void print(BaseDataDecoder decoder, Pointer ptr, ByteBuffer fileBuffer, PrintWriter pw)
	{
		int itemID;

		while ((itemID = fileBuffer.getInt()) != 0) {
			int cost = fileBuffer.getInt();
			int desc = fileBuffer.getInt();

			pw.printf("%-20s ", ProjectDatabase.getItemConstant(itemID));
			decoder.printWord(pw, cost);
			decoder.printWord(pw, desc);
			pw.println(decoder.getStringComment(desc));
		}

		pw.printf("%08X %08X %08X%n", itemID, fileBuffer.getInt(), fileBuffer.getInt());
	}
}

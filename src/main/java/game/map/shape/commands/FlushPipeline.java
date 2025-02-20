package game.map.shape.commands;

import java.io.Serializable;

import game.map.mesh.AbstractMesh;

public class FlushPipeline extends DisplayCommand implements Serializable
{
	private static final long serialVersionUID = 1L;

	public FlushPipeline(AbstractMesh parentMesh)
	{
		super(parentMesh);
	}

	@Override
	public String toString()
	{
		return "RDP Pipe Sync";
	}

	@Override
	public CmdType getType()
	{
		return CmdType.PipeSync;
	}

	@Override
	public int[] getF3DEX2Command()
	{
		return new int[] { 0xE7000000, 0x00000000 };
	}

	@Override
	public DisplayCommand deepCopy()
	{
		return new FlushPipeline(parentMesh);
	}
}

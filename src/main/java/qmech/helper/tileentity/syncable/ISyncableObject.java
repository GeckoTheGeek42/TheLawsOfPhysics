package qmech.helper.tileentity.syncable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public interface ISyncableObject {
	/*

	public void readFromStream(DataInput stream) throws IOException;

	public void writeToStream(DataOutput stream, boolean fullData) throws IOException;
	*/

	public void writeToNBT(NBTTagCompound nbt, String name);

	public void readFromNBT(NBTTagCompound nbt, String name);

	public void markDirty();
	
	public boolean isDirty();

	public void markClean();
}
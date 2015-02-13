/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��10������12:11:41
 * @version   GTKEncapsulateOOMusic V1.0
 */
public class OOMusic extends OOBin implements AutoCloseable
{

	/**
	 * @param args
	 */
	public OOMusic(String filename)
	{
		setId(GTK.mci_open(filename));
	}
	public OOMusic(String filename,boolean isFile)
	{
		setId(GTK.mci_open_from_resource(filename));
	}
	
	public void playOnce()
	{
		GTK.mci_play(this.getId(), false);
	}
	
	public void playRepeat()
	{
		GTK.mci_play(this.getId(), false);
	}

	public void pause()
	{
		GTK.mci_pause(this.getId());
	}
	public void close()
	{
		GTK.mci_close(this.getId());
	}
/*	@Override
	public void close() throws Exception
	{
		// TODO Auto-generated method stub
		
	}*/
}

/**
 * 
 */
package GTKEncapsulate;

import com.rupeng.gtk4j.GTK;

/**
 * @author    Ҷ����
 * @time      2015��2��8������9:49:26
 * @version   GTKEncapsulateOOFileAction V1.0
 */
public enum OOFileAction
{
	OPEN(GTK.GTK_FILE_CHOOSER_ACTION_OPEN),
	SAVE(GTK.GTK_FILE_CHOOSER_ACTION_SAVE),
	SELECTFOLDER(GTK.GTK_FILE_CHOOSER_ACTION_SELECT_FOLDER),
	CREATEFOLDER(GTK.GTK_FILE_CHOOSER_ACTION_CREATE_FOLDER);
	
	private int fileAction;
	private OOFileAction(int fileAction)
	{
		 this.fileAction = fileAction;
	}
	
	public int getValue()
	{
		return this.fileAction;
	}
	
	public OOFileAction parseOOFileAction(int fileAction)
	{
		OOFileAction[]  files = OOFileAction.values();
		for(int i = 0 ; i < files.length; i++)
		{
			if(files[i].getValue() == fileAction)
			{
				return files[i];
			}
		}
		throw new IllegalArgumentException("FileChooserAction = "+fileAction+"�Ǹ����Ϸ����ļ��򿪲���");
	}
}
package radar.SwingWorker;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingWorker;

import radar.SpringUtil;
import radar.UI.Components.ManagerComBox;

public class SwingWorkerForManagerComBox extends SwingWorker<Object[],Void>{
	private ManagerComBox box;
	private String className;
	private String methodName;
	private Object[] resultData = {};

	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel mode = null;

	public SwingWorkerForManagerComBox(ManagerComBox box,String className,String methodName) {		
		this.box = box;
		this.className = className;
		this.methodName = methodName;			
	}
	@Override
	protected Object[] doInBackground() throws Exception {
		Object o = SpringUtil.getBean(className);
		Method method = o.getClass().getMethod(methodName);
		Object[] result = (Object[]) method.invoke(o);
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "null" })
	@Override
	protected void done() {
		
		try {
			Object[] data = get();
			if(data!= null||data.length!=0) {
				resultData = new String[1+data.length];
				resultData[0] = "All";
				for(int i=0;i<data.length;i++) {
					resultData[i+1] =data[i];				
				}
			}
			mode = new DefaultComboBoxModel(resultData);
			box.setModel(mode);				
			} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		
	}
}

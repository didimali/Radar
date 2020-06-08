package radar.SwingWorker;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import radar.SpringUtil;
import radar.UI.Components.Table;

public class SwingWorkerForTable extends SwingWorker<Object[][],Void>{

	private Table table;
	private String className;
	private String methodName;
	
	public SwingWorkerForTable(Table table,String className,String methodName) {
		
		this.table = table;
		this.className = className;
		this.methodName = methodName;	
	}
	
	@Override
	protected Object[][] doInBackground()throws Exception {		
		Object t = SpringUtil.getBean(className);		
		Method method = t.getClass().getMethod(methodName);
		Object[][] result = (Object[][]) method.invoke(t);
		return result;
	}
	
	@Override
	protected void done() {
		try {
			Object[][] data = get();
			table.init(data);			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}

}

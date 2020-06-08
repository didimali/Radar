package radar.SwingWorker;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import radar.SpringUtil;
import radar.UI.Components.ComboBox;

public class SwingWorkerForComboBox extends SwingWorker<Object[],Void>{

	private ComboBox box;
	private String className;
	private String methodName;
	
	/*
	 * 为下拉框提供数据的工具类
	 * @param box:需要数据的下拉框
	 * @param className:下拉框服务实现层的名字
	 * @param method:下拉框服务实现层获取数据的方法名字
	 */
	
	public SwingWorkerForComboBox(ComboBox box,String className,String methodName) {		
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
	
	@Override
	protected void done() {
		
		try {
			Object[] data = get();
			box.init(data);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		
	}

}

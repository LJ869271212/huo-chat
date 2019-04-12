package cn.wildfire.chat.app.main;

/**
 * @author 000432
 * @date 2018/5/25 0025
 */

public interface MultiTypeSupport<T> {
  int getLayoutId(T item, int position);
}

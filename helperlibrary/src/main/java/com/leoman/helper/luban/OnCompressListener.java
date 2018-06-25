package com.leoman.helper.luban;

import java.io.File;

public interface OnCompressListener {

  /**
   * Fired when the compression is started, override to handle in your own code
   */
  void onCompressStart(int tag);

  /**
   * Fired when a compression returns successfully, override to handle in your own code
   */
  void onCompressSuccess(File file,int tag);

  /**
   * Fired when a compression fails to complete, override to handle in your own code
   */
  void onCompressError(Throwable e,int tag);
}

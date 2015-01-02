/*
 * Distributed as part of mchange-commons-java 0.2.9
 *
 * Copyright (C) 2015 Machinery For Change, Inc.
 *
 * Author: Steve Waldman <swaldman@mchange.com>
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of EITHER:
 *
 *     1) The GNU Lesser General Public License (LGPL), version 2.1, as 
 *        published by the Free Software Foundation
 *
 * OR
 *
 *     2) The Eclipse Public License (EPL), version 1.0
 *
 * You may choose which license to accept if you wish to redistribute
 * or modify this work. You may offer derivatives of this work
 * under the license you have chosen, or you may provide the same
 * choice of license which you have been offered here.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received copies of both LGPL v2.1 and EPL v1.0
 * along with this software; see the files LICENSE-EPL and LICENSE-LGPL.
 * If not, the text of these licenses are currently available at
 *
 * LGPL v2.1: http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 *  EPL v1.0: http://www.eclipse.org/org/documents/epl-v10.php 
 * 
 */

package com.mchange.io.impl;

import java.io.*;

public class EndsWithFilenameFilter implements FilenameFilter
{
  public final static int ALWAYS = 0;
  public final static int NEVER  = 1;
  public final static int MATCH  = 2;

  String[] endings = null;
  int      accept_dirs;

  public EndsWithFilenameFilter(String[] endings, int accept_dirs)
    {
      this.endings    = endings;
      this.accept_dirs = accept_dirs;
    }

  public EndsWithFilenameFilter(String ending, int accept_dirs)
    {
      this.endings = new String[]{ending};
      this.accept_dirs = accept_dirs;
    }

  public boolean accept(File dir, String name)
    {
      if (accept_dirs != MATCH && new File(dir, name).isDirectory()) return (accept_dirs == ALWAYS);
      for (int i = endings.length; --i >= 0;)
	if (name.endsWith(endings[i])) return true;
      return false;
    }
}

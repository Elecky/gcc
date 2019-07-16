This directory contains the GNU Compiler Collection (GCC).

The GNU Compiler Collection is free software.  See the files whose
names start with COPYING for copying permission.  The manuals, and
some of the runtime libraries, are under different terms; see the
individual source files for details.

The directory INSTALL contains copies of the installation information
as HTML and plain text.  The source of this information is
gcc/doc/install.texi.  The installation information includes details
of what is included in the GCC sources and what files GCC installs.

See the file gcc/doc/gcc.texi (together with other files that it
includes) for usage and porting information.  An online readable
version of the manual is in the files gcc/doc/gcc.info*.

See http://gcc.gnu.org/bugs/ for how to report bugs usefully.

Copyright years on GCC source files may be listed using range
notation, e.g., 1987-2012, indicating that every year in the range,
inclusive, is a copyrightable year that could otherwise be listed
individually.

# 安装说明
* 下载ecj.jar放到合适的位置（方便起见，我们在gcc/ecj文件夹下上传了实验中使用的ecj.jar文件），
并让下列命令中的-with-ecj-jar参数值指向ecj.jar的位置。<br/>
* 在**gcc目录树之外的位置**建立build文件夹，完成编译安装：
```
mkdir build
cd build
{path/to/gcc/configure} --prefix=$HOME/local --with-fpmath=sse --disable-bootstrap --enable-languages=c++,java --enable-checking='yes' --disable-multilib -with-ecj-jar={path/to/ecj.jar}
make -j $(nproc)
make install
```
# 使用说明
1. 使用gcj将Java源代码编译成.class文件，然后打包为jar文件
2. 使用“各命令说明”中3、4、5中任意一种方案将jar文件编译成共享库。如果是PATCH方案，则继续按照第6步完成patch（patch操作是必须的）
3. 按照“各命令说明”中第7，8步创建映射db，并添加对应关系到db中
4. 按照“各命令说明”中第9步运行测试
### 例
假设已有lib.jar和app.jar，使用静态方案编译lib.jar，PATCH方案编译app.jar
```
gcj -shared -fPIC -O1 lib.jar -o lib.so
gcj -shared -findirect-dispatch -Wl,-Bsymbolic -fjni -fPIC -O1 -fpatch-directive app.jar -o app.so
gij --cp lib.jar:app.jar --patch app.so any
gcj-dbtool -n myapp.db
gcj-dbtool -a myapp.db lib.jar lib.so
gcj-dbtool -a myapp.db app.jar app.so.patch
gij -Dgnu.gcj.precompiled.db.path=myapp.db --cp lib.jar:app.jar Test
```

# 各命令说明
1. 编译java文件为.class文件：
```
gcj -C -classpath path/to/classpath *.java
```
2. 将jar文件编译为可执行文件：
```
gcj -O1 xxx.jar -o xxx
```
3. 将jar文件以静态编译方案编译为共享库：
```
gcj -shared -fPIC -O1 xxx.jar -o xxx.so
```
4. 将jar文件以BC-ABI方案编译为共享库：
```
gcj -shared -findirect-dispatch -Wl,-Bsymbolic -fjni -fPIC -O1 xxx.jar -o xxx.so
```
5. 将jar文件以PATCH方案编译为共享库：
```
gcj -shared -findirect-dispatch -Wl,-Bsymbolic -fjni -fPIC -O1 -fpatch-directive xxx.jar -o xxx.so
```
6. 对编译完成的共享库进行patch，注意patch时不会修改原文件，而是生成一个`xxx.so.patched`文件作为输出，用xxlib.jar表示xxx.jar依赖的库：
```
gij --cp xxlib.jar:xxx.jar --patch xxx.so any
```
7. 创建映射db：
```
gcj-dbtool -n myapp.db
```
8. 向映射db添加一组jar-so文件的对应：
```
gcj-dbtool -a myapp.db xxx.jar xxx.so|xxx.so.patch
```
9. 运行测试，假设main函数所在类为Test
```
gij -Dgnu.gcj.precompiled.db.path=myapp.db --cp xxlib.jar:xxx.jar Test
```

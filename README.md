# Project Overview
This is my spring-boot demo and some simple exercises.

## FAQ
* Navicat连接mysql报错1251解决方案 &lt;br&gt;
        1. cmd进入mysql的账户
        ```powershell
        c:\windows\system32> mysql -uroot -p
        ```
        2. 再输入密码
        3. 更改加密方式
        ```powershell
        alter user 'root'@'localhost' IDENTIFIED by 'password' PASSWORD EXPIRE NEVER;
        ```
        4. 更改密码方式
        ```powershell
        ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';
        ```
        5. 刷新
        ```powershell
        FLUSH PRIVILEGES;
        ```

# Reference
https://blog.csdn.net/softwave/article/details/77152373

# menu用户个性化菜单

 
 menu   菜单主实体
 
 Menu   需要再加工
 
     保存数据库的菜单对象，因为系统实现的是根据个人的权限不同，显示的不同的菜单，
     且不同的菜单具有不同的读写权限，这些都是跟会员的id关联，，
     而且公司的管理员，具有分配 公司里面其他员工 权限的功能。
     
     所以如果此userId不是default 菜单，那么他拥有的具体菜单有哪些，名字，url，权限，
     应该保存在数据库，所以这个就是 此数据库对象
     
     
MenuControlAuthorities 

    这是一个enum，功能； 针对菜单的控制权限，包含了读写改删  的属性定义
    
MenuDto

    这个是前台需要显示菜单的时候， 包含显示的url，name
    
MenuType

    这个是目前所有的 菜单集合，是一个enum
    
ResponseWhenQueryMenus

    当用户登录后，返回给前台的menu list数组，和用户的姓名的dto

    
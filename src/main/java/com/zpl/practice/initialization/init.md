# 成员变量的初始化


## 起因
来源牛客网中一道做错的习题
```java
链接：https://www.nowcoder.com/questionTerminal/c2bfb1512dfa4a7eab773a5871a52402
来源：牛客网

public class Base
{
    private String baseName = "base";
    public Base()
    {
        callName();
    }
 
    public void callName()
    {
        System. out. println(baseName);
    }
 
    static class Sub extends Base
    {
        private String baseName = "sub";
        public void callName()
        {
            System. out. println (baseName) ;
        }
    }
    public static void main(String[] args)
    {
        Base b = new Sub();
    }
}
```
我当时的想法是这样的：
- 创建`new Sub()`调用Sub类的构造方法，然后Sub构造方法的第一行隐式的使用`super()`调用Base类的构造方法，在Base类的构造方法中调用了`callName()`方法，输出了`baseName`属性，所以我直接选了base
- **答案是`null`**，我看了下问题下面的解析，思路基本上是这样的：创建`new Sub()`调用Sub类的构造方法，然后Sub构造方法的第一行隐式的使用`super()`调用Base类的构造方法，在Base类的构造方法中调用了Sub类中的`callName()`方法，然后`callName()`方法输出Sub中的`baseName`，此时`baseName`并未初始化，所以输出是`null`
- 然后我的问题就来了
	1. 为什么父类中调用了子类的`callName()`方法
	2. 为什么在调用子类`callName()`方法时，`baseName`属性未被初始化

## 测试
- 为了方便我将父类和子类拆开来进行分析，代码如下：
```java
public class Base {

    private String baseName = "Base";

    public Base(){
        callName();
    }

    public void callName(){
        System.out.println(baseName);
    }
}
```
```java
public class Sub extends Base {

    private String baseName = "Sub";

    @Override
    public void callName(){
        System.out.println(baseName);
    }

    public static void main(String[] args) {
		new Sub();
    }

}
```
### 为什么父类调用了子类的`callName()`方法
- 这个部分是我一开始挺不理解的地方，明明是在父类的构造方法中调用了`callName()`方法，实际上执行的却是子类的`callName()`方法
- 我试着改了下代码确认了一下，Base的callName()方法中输出"base"，在Sub的callName()方法中输出“sub”，然后创建Sub类的实例`Base b = new Sub()`，结果确实是输出了sub（说明确实调用的是子类的`callName()`方法）
	1. 那么Base中的callName()方法不就没用了吗？
		- 创建了Base类的实例`Base c = new Base()`（创建的是父类实例），这时结果输出了base（调用的是父类的`callName()`方法）
	2. 我子类里要是没有callName()方法，你调谁去？
		- 删除Sub中的callName()方法，然后再执行Sub实例创建`Base b = new Sub()`（创建的的是子类实例），这时结果输出base
- 那么父类调用子类方法就是有前提的
	- 必须是创建子类对象`new Sub()`的时候，这个其实可以这样想，如果创建Base实例`new Base()`的时候调用了子类的方法，那么如果Base有两个子类，我们是不知道会调用哪个子类的方法的，而创建子类实例`new Sub()`时，我们是知道要创建的是哪个子类的，所以就知道要调用哪个子类的方法，这点可以从上面1中看出来，只不过没有写第二个子类
	- 再一个就是这个方法得被子类重写，如果没有被子类重写的话调用的还是父类的方法，这点可以从上面2看出来
- [这是我百度出的一个对于这个的问题的文章](https://blog.csdn.net/zhuoaiyiran/article/details/19489745)，文章中指出这是一个多态问题，知道问题就好办了

### 多态

> Java引用变量有两个类型：一个是编译时类型，一个是运行时类型。编译时类型由声明该变量时使用的类型决定，运行时类型由实际赋值给该变量的对象决定。如果编译时类型和运行时类型不一致，就可能出现所谓的多态

- 按照上面的说法，在`Base b = new Sub()`中，Base为编译时类型，Sub为运行时类型

>因为子类其实是一种特殊的父类，因此Java允许把一个子类对象直接赋值给一个父类引用变量，无须任何类型转换，或称为向上转型（upcasting），向上转型由系统自动完成

>对于`Base b = new Sub()`，b这个引用变量的编译时类型就是Base，而运行时类型是Sub，**当运行时调用该引用变量的方法时**，其方法行为总是表现出子类方法的行为特征，而不是父类方法的行为特征，这就可能出现：相同类型的变量、调用同一方法时呈现多重不同的行为特征，这就是多态

- 编译时类型我理解为写在纸面上（编译）是这个类型，表现的特征也就是这个类型，因为这个类的构造在编译时是可以确定的，这里的表现特征我指的是方法，即假设Base有方法A，Sub中重写了方法A且新增了方法B，**那么使用b这个引用时，是不能调用方法B的，即便实际上是Sub的实例，因为Base在编译的时候没有方法B，所以不能调用**
- 运行时类型我理解为实际在运行的类型，也就是实际执行方法的类型，即b在调用方法A时实际执行的是Sub中的方法A，而不是Base中的方法A。现在反过来假设：Base中有方法A和方法B，而Sub中重写了方法A，那么当b调用方法B的时候，因为Sub中没有方法B，实际调用的是从Base中继承来的方法B，但这个时候是可以写b调用方法B的

>与方法不同的是，**对象的实例变量则不具备多态性**。假设Base和Sub中都有变量a，那么b.a输出的会是Base中的实例变量而不是Sub中的实例变量。

>通过引用变量来访问其包含的实例变量时，系统总是试图访问它编译时类型所定义的成员变量，而不是它运行时类型所定义的成员变量

- 想要输出运行时类型定义的成员变量，可以通过方法来返回
- 方法是动态绑定，成员变量是静态绑定

### 为什么调用子类的时候方法的时候，变量没有初始化
- 这个问题也是我很不理解的地方，这两个变量都是在声明的时候进行了初始化，那么声明时初始化是什么时候初始化的？
- 我先进行了如下测试：
```java
public class Init {
    /**
     * 声明变量name时，将name初始化为张三
     */
    private String name = "张三";

    public Init(){
        //输出name,此时为张三
        System.out.println(name);    //1
        //将name赋值为李四
        this.name = "李四";
    }

    public static void main(String[] args) {
        //输出张三
        Init init = new Init();
        //输出李四
        System.out.println(init.name);
    }
}
```
- 从上面的例子看来，好像在进行构造方法之前声明时初始化就已经执行了，所以在`1`会输出"张三"
- 但是回到我们的问题中，如果声明时初始化在构造方法执行之前执行，那么在执行Base类构造方法的时候，肯定先执行了Sub类的构造方法，因为构造方法第一行默认会使用`super()`调用父类构造方法，即先执行Sub的构造方法，执行到第一句调用`super()`时执行Base构造方法，然后Base构造方法再调用callName()方法，但这时候Sub的构造方法已经开始执行了，那么baseName变量应该有值才对，不应该是null
- 再一个问题，我们经常说调用构造方法创建实例，但实际上创建实例（在内存中划分区域给实例）这部分操作并不由构造方法来进行，构造方法的作用是在其内部使用`this`来指代实例来进行初始化。那么反过来讲，不使用构造方法的话，是不会在内存中划分区域给实例的，那么连实例都没有的话，在创建实例之间就执行声明时初始化这是说不通的，实例都没有你初始化什么属性啊，那么声明时初始化在构造方法执行之前执行也是说不通的
- 那么问题又回来了，声明时初始化到底是什么时候执行的？
- 我打了个断点看了下`Base b = new Sub()`到底是怎么执行的，代码如下：
```java
public class Sub extends Base {

    public String baseName = "Sub";    //8

    public Sub(){	//1
        System.out.println(baseName);    //9
    }    //10
    
    @Override
    public void callName(){
        System.out.println(baseName);    //5
    }    //6

    public static void main(String[] args) {
        Base b = new Sub();
    }
}
```
```java
public class Base {

    public String baseName = "Base";    //3

    public Base(){    //2
        callName();    //4
    }    //7

    public void callName(){
        System.out.println("base");
    }
}
```
1. 进入Sub构造方法，走到Sub的`1`，此时Sub的baseName是null
2. 进入Base构造方法，走到Base的`2`，此时Base的baseName是null
3. 走到Base的`3`，此时Base的baseName是null
4. 走到Base的`4`，此时Base的baseName是“base”
5. 走到Sub的`5`，此时Sub的baseName是null
6. 走到Sub的`6`，输出null，此时Sub的baseName是null
7. 走到Base的`7`
8. 走到Sub的`8`，此时Sub的baseName是null
9. 走到Sub的`9`，此时Sub的baseName是"sub"
10. 走到Sub的`10`
11. 执行结束
- 从上面步骤中，我发现：
	1. 执行Sub构造方法时，没有进行声明时初始化就直接执行Base的构造方法了
	2. 而在Base的构造方法中执行callName方法之前执行了声明时初始化
	3. 在Base的构造方法执行完之后，继续执行Sub构造方法时先执行了声明时初始化
###Java对象的创建及实例化顺序
- Java在创建子类实例的时候会先调用父类的构造方法（显示或隐式的`super()`）
	- 这样做应该是因为成员变量是静态绑定的，需要先初始化父类的成员变量
	- 但是这里调用父类构造方法是不生成父类实例的，[详情看这里](https://blog.csdn.net/qq_27760433/article/details/72889104)
- 在初始化完父类成员变量后（结束`super()`后），生成子类实例（内存中划分区域），然后进行子类实例的初始化：
	1. 执行声明时初始化
	2. 若有非静态代码块执行非静态代码块
	3. 执行构造方法的初始化部分

>实例的初始化顺序就是**父类的静态变量<=>父类的静态块->子类的静态变量<=>子类的静态块（静态变量和静态块执行顺序是以它们在程序里出现的先后顺序来顺序执行的）->（父类的实例化变量->父类的实例化块->）父类的构造方法->(子类的实例化变量->子类的实例化块->)子类的构造方法**

- [这篇文章总结的比较清楚](https://blog.csdn.net/qq_24892029/article/details/51278279)

### 结论
这道题的问题就是在创建子类对象（`new Sub()`），调用父类构造方法时，父类方法调用了动态绑定的被子类重写的方法（子类的`callName()`），此时子类还没有进行初始化，所以成员变量均为null，所以输出null

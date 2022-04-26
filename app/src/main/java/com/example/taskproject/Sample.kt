package com.example.taskproject

class Sample {
    fun main() {


        /*
    * Scope functions 'also'
    * Property 1:refer to context object by using 'it'
    * Property 2:The return value is the 'context object'*/
        val numbersList: MutableList<Int> = mutableListOf(1, 2, 3)
        numbersList.also {
            println("The list elements are $it")
            numbersList.add(4)
            numbersList.removeAt(2)
            println("The list elements are $it")
        }
    }
    /*
    * Scope functions 'let'
    * Property 1:Refer to context object by using 'it'
    * The return value is the 'lambda result'
    * Use 'let' function to avoid Null PointerException
    **/
 fun test()
    {
     val name:String?="hello"
      val stringLength=  name.let {
            println(it?.reversed())
            println(it?.capitalize())
            println(it?.length)
        }
        println(name?.reversed())
        println(stringLength)

 }
    /*
    * Scope function run
    * */
}
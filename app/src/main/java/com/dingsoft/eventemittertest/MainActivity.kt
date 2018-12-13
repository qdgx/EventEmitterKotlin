package com.dingsoft.kotlintest

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import com.dingsoft.eventemitter.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        mapNormalTest.setOnClickListener {
//            testMapNormal();
//        }
//        mapErrorTest.setOnClickListener {
//            testMapError();
//        }
//        flatMapNormalTest.setOnClickListener {
//            testFlatMapNormal();
//        }
//        flatMapErrorTest.setOnClickListener {
//            testFlatMapError();
//        }
        eventOnTest.setOnClickListener {
            val eventEmitterImpl: EventEmitterImpl = EventEmitterImpl()
//            eventEmitterImpl.on("key", object: EventEmitterInterface.Listener(){
//                override fun onEvent(vararg args: Any) {
//                    var sb: String = String()
//                    for (arg in args){
//                        sb += (" " + arg)
//                    }
//                    log("key: $sb")
//                }
//            })
//            eventEmitterImpl.on("key",{ args ->
//                    var sb: String = String()
//                    for (arg in args){
//                        sb += (" " + arg)
//                    }
//                    log("key: $sb")
//            })

            eventEmitterImpl.on("key"){ args ->
                var sb: String = String()
                for (arg in args){
                    sb += (" " + arg)
                }
                log("key: $sb")
            }
            eventEmitterImpl.emit("key",101)
            eventEmitterImpl.emit("key","hello world")
            eventEmitterImpl.emit("key","hello world",101,102.3f,"end")
        }

        eventOnceTest.setOnClickListener {
            val eventEmitterImpl: EventEmitterImpl = EventEmitterImpl()
            eventEmitterImpl.once("key"){ args ->
                    var sb: String = String()
                    for (arg in args){
                        sb += (" " + arg)
                    }
                    log("key: $sb")
                }
            eventEmitterImpl.emit("key",101)
            eventEmitterImpl.emit("key","hello world")
            eventEmitterImpl.emit("key","hello world",101,102.3f,"end")
        }

        eventRemoveTest.setOnClickListener {
            val eventEmitterImpl: EventEmitterImpl = EventEmitterImpl()
            var eventListener = { args: Array<out Any> ->
                var sb: String = String()
                for (arg in args){
                    sb += (" " + arg)
                }
                log("key: $sb")
            }
            eventEmitterImpl.on("key",eventListener)
            eventEmitterImpl.emit("key",101)
            eventEmitterImpl.emit("key","hello world")
            eventEmitterImpl.removeListener("key",eventListener)
            eventEmitterImpl.emit("key","hello world")
        }

        eventRemoveAllByNameTest.setOnClickListener {
            val eventEmitterImpl: EventEmitterImpl = EventEmitterImpl()
            eventEmitterImpl.on("key"){ args ->
                    var sb: String = String()
                    for (arg in args){
                        sb += (" " + arg)
                    }
                    log("key: $sb")
                }
            eventEmitterImpl.emit("key",101)
            eventEmitterImpl.emit("key","hello world")
            eventEmitterImpl.removeAllListeners("key")
            eventEmitterImpl.emit("key","hello world")
        }

        eventRemoveAllTest.setOnClickListener {
            val eventEmitterImpl: EventEmitterImpl = EventEmitterImpl()
            eventEmitterImpl.on("key"){ args ->
                    var sb: String = String()
                    for (arg in args){
                        sb += (" " + arg)
                    }
                    log("key: $sb")
                }
            eventEmitterImpl.emit("key",101)
            eventEmitterImpl.emit("key","hello world")
            eventEmitterImpl.removeAllListeners()
            eventEmitterImpl.emit("key","hello world")
        }

        eventSetMaxTest.setOnClickListener {
            val eventEmitterImpl: EventEmitterImpl = EventEmitterImpl()
            eventEmitterImpl.setMaxListeners(3)
            for(i in 1..5){
                eventEmitterImpl.on("key"){ args ->
                        var sb: String = String()
                        for (arg in args){
                            sb += " $arg"
                        }
                        log("$i key: $sb")
                    }
            }
            eventEmitterImpl.emit("key",101)
            eventEmitterImpl.emit("key","hello world")
            eventEmitterImpl.emit("key","hello world")
        }

        eventListenerCountTest.setOnClickListener {
            val eventEmitterImpl: EventEmitterImpl = EventEmitterImpl()
            for(i in 1..5){
                eventEmitterImpl.on("key"){ args ->
                        var sb: String = String()
                        for (arg in args){
                            sb += " $arg"
                        }
                        log("$i key: $sb")
                    }
                eventEmitterImpl.emit("key",101)
                eventEmitterImpl.emit("key","hello world")
            }
            var count = eventEmitterImpl.listenerCount("key")
            log("listener count $count")
        }

        eventOnMemoryTest1.setOnClickListener {
            var loops = 100000
            for(i in 1..loops){
                val eventEmitterImpl: EventEmitterImpl = EventEmitterImpl()
                eventEmitterImpl.on("key"){ args ->
                        var sb: String = String()
                        for (arg in args){
                            sb += " $arg"
                        }
                        log("$i key: $sb")
                    }
                eventEmitterImpl.emit("key",101)
                eventEmitterImpl.emit("key","hello world")
            }
        }

        eventOnMemoryTest2.setOnClickListener {
            var loops = 100000
            val eventEmitterImpl: EventEmitterImpl = EventEmitterImpl()
            eventEmitterImpl.on("key"){ args ->
                    var sb: String = String()
                    for (arg in args){
                        sb += " $arg"
                    }
                    log("key: $sb")
                }
            for(i in 1..loops){
                eventEmitterImpl.emit("key",101)
                eventEmitterImpl.emit("key","hello world")
            }
        }

        eventOnMemoryThreadTest.setOnClickListener {
            //thread test
            Thread(object : Runnable{
                override fun run() {
                    var loops = 100000
                    for(i in 1..loops){
                        val eventEmitterImpl: EventEmitterImpl = EventEmitterImpl()
                        eventEmitterImpl.on("key"){ args ->
                                var sb: String = String()
                                for (arg in args){
                                    sb += " $arg"
                                }
                                log("$i key: $sb")
                            }
                        eventEmitterImpl.emit("key",101)
                        eventEmitterImpl.emit("key","hello world")
                    }
                }
            }).start()
        }

        eventOnMemoryThreadTest.setOnClickListener {
            //thread test
            Thread(object : Runnable{
                override fun run() {
                    var loops = 100000
                    for(i in 1..loops){
                        val eventEmitterImpl: EventEmitterImpl = EventEmitterImpl()
                        eventEmitterImpl.on("key"){ args ->
                                var sb: String = String()
                                for (arg in args){
                                    sb += " $arg"
                                }
                                log("$i key: $sb")
                            }
                        eventEmitterImpl.emit("key",101)
                        eventEmitterImpl.emit("key","hello world")
                    }
                }
            }).start()
        }

        eventOnMemoryHandleUITest.setOnClickListener {
            //thread test
            Thread(object : Runnable{
                override fun run() {
                    var loops = 100000
                    for(i in 1..loops){
                        val eventEmitterImpl: EventEmitterImpl = EventEmitterImpl()
                        eventEmitterImpl.on("key"){ args ->
                                var sb: String = String()
                                for (arg in args){
                                    sb += " $arg"
                                }
                                var logMessage = "$i key: $sb"
                                log(logMessage)
                                var message = Message.obtain()
                                message.obj = logMessage
                                message.what = 1
                                mHandler.sendMessage(message)
                            }
                        eventEmitterImpl.emit("key",101)
                        eventEmitterImpl.emit("key","hello world")
                    }
                }
            }).start()
        }


        eventOnceMemoryTest1.setOnClickListener {
            var loops = 100000
            for(i in 1..loops){
                val eventEmitterImpl: EventEmitterImpl = EventEmitterImpl()
                eventEmitterImpl.once("key"){ args ->
                        var sb: String = String()
                        for (arg in args){
                            sb += " $arg"
                        }
                        log("$i key: $sb")
                    }
                eventEmitterImpl.emit("key",101)
                eventEmitterImpl.emit("key","hello world")
            }
        }

        eventOnceMemoryTest2.setOnClickListener {
            var loops = 100000
            val eventEmitterImpl: EventEmitterImpl = EventEmitterImpl()
            eventEmitterImpl.once("key"){ args ->
                    var sb: String = String()
                    for (arg in args){
                        sb += " $arg"
                    }
                    log("key: $sb")
                }
            for(i in 1..loops){
                eventEmitterImpl.emit("key",101)
                eventEmitterImpl.emit("key","hello world")
            }
        }
    }

    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                1->{
                    textView1.text = msg.obj as String
                }
            }
        }
    }

    private fun log(msg: String) {
        Log.e(javaClass.simpleName, msg)
    }

}

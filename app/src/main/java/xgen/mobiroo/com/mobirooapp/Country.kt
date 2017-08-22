package xgen.mobiroo.com.mobirooapp

/**
 * Created by Raymond Zhuang on 2016-06-18.
 */
class Country {
    lateinit var shortName: String
    lateinit var fullName: String
        private set

    fun setfullName(name: String) {
        fullName = name
    }
}

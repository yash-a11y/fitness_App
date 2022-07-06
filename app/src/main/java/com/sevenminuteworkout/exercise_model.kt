package com.sevenminuteworkout

class exercise_model
    (
    private var id : Int,
    private var name : String,
    private var image : Int,
    private var iscompleted : Boolean,
    private var isselected : Boolean
    )

{
        fun getId() : Int{
            return id
        }

        fun setId(id : Int) {
            this.id = id
        }

        fun setName(name : String){
            this.name = name
        }

        fun getName() : String
        {
            return name
        }

        fun setImage(image : Int)
        {
            this.image = image
        }

        fun getImage() : Int{
            return image
        }

        fun getIsCompleted() : Boolean{
            return iscompleted
        }

        fun setIsCompleted(iscompleted: Boolean){
            this.iscompleted = iscompleted
        }

        fun setIsselected(isselected: Boolean){
            this.isselected = isselected
        }

        fun getIsselected() : Boolean{
            return isselected
        }

    }
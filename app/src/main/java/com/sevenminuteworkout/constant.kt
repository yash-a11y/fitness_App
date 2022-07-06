package com.sevenminuteworkout

class constant {
    companion object
    {
        fun defaultExerciseLists() : ArrayList<exercise_model>{

            val exerciseList = ArrayList<exercise_model>()

            val jumpingjacks =
                exercise_model(1,"jumping jacks",R.drawable.ic_jumping_jacks,false,false)
            exerciseList.add(jumpingjacks)

            val wallSit = exercise_model(2, "Wall Sit", R.drawable.ic_wall_sit, false, false)
            exerciseList.add(wallSit)

            val pushUp = exercise_model(3, "Push Up", R.drawable.ic_push_up, false, false)
            exerciseList.add(pushUp)

            val abdominalCrunch =
                exercise_model(4, "Abdominal Crunch", R.drawable.ic_abdominal_crunch, false, false)
            exerciseList.add(abdominalCrunch)

            val stepUpOnChair =
                exercise_model(
                    5,
                    "Step-Up onto Chair",
                    R.drawable.ic_step_up_onto_chair,
                    false,
                    false
                )
            exerciseList.add(stepUpOnChair)

            val squat = exercise_model(6, "Squat", R.drawable.ic_squat, false, false)
            exerciseList.add(squat)

            val tricepDipOnChair =
                exercise_model(
                    7,
                    "Tricep Dip On Chair",
                    R.drawable.ic_triceps_dip_on_chair,
                    false,
                    false
                )
            exerciseList.add(tricepDipOnChair)

            val plank = exercise_model(8, "Plank", R.drawable.ic_plank, false, false)
            exerciseList.add(plank)

            val highKneesRunningInPlace =
                exercise_model(
                    9, "High Knees Running In Place",
                    R.drawable.ic_high_knees_running_in_place,
                    false,
                    false
                )
            exerciseList.add(highKneesRunningInPlace)

            val lunges = exercise_model(10, "Lunges", R.drawable.ic_lunge, false, false)
            exerciseList.add(lunges)

            val pushupAndRotation =
                exercise_model(
                    11,
                    "Push up and Rotation",
                    R.drawable.ic_push_up_and_rotation,
                    false,
                    false
                )
            exerciseList.add(pushupAndRotation)

            val sidePlank = exercise_model(12, "Side Plank", R.drawable.ic_side_plank, false, false)
            exerciseList.add(sidePlank)
            return exerciseList
        }
    }
}
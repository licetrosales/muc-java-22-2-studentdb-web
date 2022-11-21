
import {Student} from "../model/Student";

type StudentProps = {
   student : Student
}

export default function StudentInfo(props: StudentProps){

    return (
        <section>
            <div>Student ID: {props.student.id} </div>
            <div>Student name: {props.student.name} </div>
        </section>
    )
}
import {Student} from "../model/Student";
import StudentInfo from "./StudentInfo";



type StudentListProps = {
    students : Student[]
}

export default function StudentList(props : StudentListProps){
const singleStudentInfo =props.students.map((std) =>{
    return (
        <StudentInfo  key={std.id} student = {std} />
    )
})
    return (
        <section>{singleStudentInfo}</section>

    )
}
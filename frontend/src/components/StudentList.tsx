import {Student} from "../model/Student";
import StudentInfo from "./StudentInfo";



type StudentListProps = {
    students : Student[],
    handleDeleteStudent(id: string): void
}

export default function StudentList(props : StudentListProps){
const singleStudentInfo =props.students.map((std) =>{
    return (
        <StudentInfo  key={std.id} student = {std} handleDeleteStudent={props.handleDeleteStudent} />
    )
})
    return (
        <section>
            <h3>Student List</h3>
            <div>{singleStudentInfo}</div>
        </section>

    )
}
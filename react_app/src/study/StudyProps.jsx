// export default function StudyProps(props) {
// export default function StudyProps({age, user}) {

// m = {age: 20, user="iu"} 형식으로 왔을 때
export default function StudyProps(m) {

  // console.log(props);
  // console.log(age, user);
  console.log(m.user);

  return(
    <>
      <h1>Props Page</h1>
    </>
  )
}
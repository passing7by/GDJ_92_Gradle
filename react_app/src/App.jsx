import List from './components/board/List.jsx';
import StudyState from './study/StudyState.jsx';
import StudyEffect from './study/StudyEffect.jsx';
import StudyRef from './study/StudyRef.jsx';
import Add from './components/board/Add.jsx';
import Header from './layout/Header.jsx';
import { BrowserRouter } from 'react-router-dom';
import AppRoutes from './route/AppRoutes.jsx';
import StudyProps from './study/StudyProps.jsx';

function App() {

  let age = 10;
  let m = { age: 20, user:'iu' };

  return (
      <>
        <Header/>
        {/* <StudyProps age='20' name='winter'/> */}
        {/* <StudyProps user={m}/> */}
        {/* <StudyProps user={m}/> */}
        <AppRoutes/>
      </>
  )
}

export default App

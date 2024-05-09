import logo from './logo.svg';
import './App.css';

const hennesy = {
  nombre: 'John L. Hennessy',
  foto: './res/hennessy.jpg',
  nacimiento: '22 de septiembre de 1952 en Huntington, Nueva York, EE. UU.',
  educacion:  'Licenciatura en Ingeniería Eléctrica, 1973 - Universidad Villanova.\n'+
              'Maestría en Ciencias en Ingeniería Eléctrica, 1975 - Universidad de Stanford.\n'+
              'Doctorado en Ciencias en Ingeniería Eléctrica, 1977 - Universidad de Stanford.\n',
  carrera:  'Profesor en la Universidad de Stanford, desde 1977.\n'+
            'Presidente de la Universidad de Stanford, 2000-2016.\n',
  contribuciones: 'Desarrollo de la arquitectura RISC (Reduced Instruction Set Computer) junto a David Patterson.\n'+
                  'Investigación en diseño de microprocesadores y sistemas informáticos.\n',
  premios:  'Premio Turing en 2017 por sus contribuciones a la arquitectura de computadoras, compartido con David Patterson.\n'+
            'Miembro de la Academia Nacional de Ingeniería y de la Academia Nacional de Ciencias de EE. UU.\n',
  bibliografia: '"Computer Architecture: A Quantitative Approach" (con David A. Patterson).\n'+
                '"Computer Organization and Design: The Hardware/Software Interface" (con David A. Patterson).\n'+
                '"The MIPS-X RISC Microprocessor" (con David A. Patterson y Norman Jouppi).\n'+
                '"Computer Architecture: Concepts and Case Studies" (con David A. Patterson).\n'+
                '"Computer Architecture: Pipelined and Parallel Processor Design" (con David A. Patterson).\n'
}

const patterson = {
  nombre: 'David Patterson',
  foto: './res/patterson.jpg',
  nacimiento: '16 de noviembre de 1947 en Evergreen Park, Illinois, EE. UU.',
  educacion:  'Licenciatura en Ciencias en Física, 1969 - Universidad de California, Los Ángeles.\n'+
              'Maestría en Ciencias en Informática, 1977 - Universidad de California, Berkeley.\n'+
              'Doctorado en Informática, 1977 - Universidad de California, Berkeley.\n',
  carrera:  'Profesor en la Universidad de California, Berkeley, desde 1977.\n'+
            'Decano de la Facultad de Informática de Berkeley.\n',
  contribuciones: 'Desarrollo de la arquitectura RISC (Reduced Instruction Set Computer).\n'+
                  'Investigación en memoria caché y sistemas informáticos distribuidos.\n',
  premios:  'Premio Turing en 2017 por sus contribuciones a la arquitectura de computadoras.\n'+
            'Otros premios y reconocimientos en la comunidad informática.\n',
  bibliografia: '"Computer Architecture: A Quantitative Approach" (con John L. Hennessy).\n'+
                '"Computer Organization and Design: The Hardware/Software Interface" (con John L. Hennessy).\n'+
                '"Quantitative Research for the Behavioral Sciences: A Book of Readings" (con Robert B. Zajonc y George A. Miller).\n'+
                '"The Datacenter as a Computer: An Introduction to the Design of Warehouse-Scale Machines" (con Luiz André Barroso y Urs Hölzle).\n'+
                '"Structured Computer Organization" (con Andrew S. Tanenbaum).\n'
}

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;

import fotoHennessy from './res/hennessy.jpg';
import fotoPatterson from './res/patterson.jpg';
import './App.css';
import Autor from './Autor';
import Glosario from './Glosario';

const hennesy = {
  nombre: 'John L. Hennessy',
  foto: fotoHennessy,
  nacimiento: '22 de septiembre de 1952 en Huntington, Nueva York, Estados Unidos.',
  educacion:  ['Licenciatura en Ingeniería Eléctrica, 1973 - Universidad Villanova.',
              'Maestría en Ciencias en Ingeniería Eléctrica, 1975 - Universidad de Stanford.',
              'Doctorado en Ciencias en Ingeniería Eléctrica, 1977 - Universidad de Stanford.'],
  carrera:  ['Profesor en la Universidad de Stanford, desde 1977.',
            'Presidente de la Universidad de Stanford, 2000-2016.'],
  contribuciones: ['Desarrollo de la arquitectura RISC (Reduced Instruction Set Computer/Conjunto de Instrucciones Reducido de Computador) junto a David Patterson.',
                  'Investigación en diseño de microprocesadores y sistemas informáticos.'],
  premios:  ['Premio Turing en 2017 por sus contribuciones a la arquitectura de computadoras, compartido con David Patterson.',
            'Miembro de la Academia Nacional de Ingeniería y de la Academia Nacional de Ciencias de Estados Unidos.'],
  bibliografia: ['"Computer Architecture: A Quantitative Approach" (con David A. Patterson).',
                '"Computer Organization and Design: The Hardware/Software Interface" (con David A. Patterson).',
                '"The MIPS-X RISC Microprocessor" (con David A. Patterson y Norman Jouppi).',
                '"Computer Architecture: Concepts and Case Studies" (con David A. Patterson).',
                '"Computer Architecture: Pipelined and Parallel Processor Design" (con David A. Patterson).']
}

const patterson = {
  nombre: 'David Patterson',
  foto: fotoPatterson,
  nacimiento: '16 de noviembre de 1947 en Evergreen Park, Illinois, Estados Unidos.',
  educacion:  ['Licenciatura en Ciencias en Física, 1969 - Universidad de California, Los Ángeles.',
              'Maestría en Ciencias en Informática, 1977 - Universidad de California, Berkeley.',
              'Doctorado en Informática, 1977 - Universidad de California, Berkeley.'],
  carrera:  ['Profesor en la Universidad de California, Berkeley, desde 1977.',
            'Decano de la Facultad de Informática de Berkeley.'],
  contribuciones: ['Desarrollo de la arquitectura RISC (Reduced Instruction Set Computer/Conjunto de Instrucciones Reducido de Computador).',
                  'Investigación en memoria caché y sistemas informáticos distribuidos.'],
  premios:  ['Premio Turing en 2017 por sus contribuciones a la arquitectura de computadoras.',
            'Otros premios y reconocimientos en la comunidad informática.'],
  bibliografia: ['"Computer Architecture: A Quantitative Approach" (con John L. Hennessy).',
                '"Computer Organization and Design: The Hardware/Software Interface" (con John L. Hennessy).',
                '"Quantitative Research for the Behavioral Sciences: A Book of Readings" (con Robert B. Zajonc y George A. Miller).',
                '"The Datacenter as a Computer: An Introduction to the Design of Warehouse-Scale Machines" (con Luiz André Barroso y Urs Hölzle).',
                '"Structured Computer Organization" (con Andrew S. Tanenbaum).']
}

const enlaces = {
  microprocesador: 'https://dle.rae.es/microprocesador',
  risc: 'https://es.wikipedia.org/wiki/Reduced_instruction_set_computing',
  cache: 'https://es.wikipedia.org/wiki/Cach%C3%A9_(inform%C3%A1tica)'
}

function App() {
  return (
    <div style={{paddingLeft: 20, paddingBottom: 20, paddingTop: 20, backgroundColor: "#FDEFE2"}}>
      <h1>Autores de las prácticas</h1>
      <Autor person={{nombre: hennesy.nombre, foto: hennesy.foto, nacimiento: hennesy.nacimiento, educacion: hennesy.educacion, 
        carrera: hennesy.carrera, contribuciones: hennesy.contribuciones, premios: hennesy.premios, bibliografia: hennesy.bibliografia}}/>
      <Autor person={{nombre: patterson.nombre, foto: patterson.foto, nacimiento: patterson.nacimiento, educacion: patterson.educacion,
        carrera: patterson.carrera, contribuciones: patterson.contribuciones, premios: patterson.premios, bibliografia: patterson.bibliografia}}/>
      <h2>Glosario del tema</h2>
      <Glosario link={{nombre: "Microprocesador", enlace: enlaces.microprocesador}}/>
      <Glosario link={{nombre: "RISC (Reduced Instruction Set Computer)", enlace: enlaces.risc}}/>
      <Glosario link={{nombre: "Cache", enlace: enlaces.cache}}/>
    </div>
  );
}

export default App;

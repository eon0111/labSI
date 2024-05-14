import Imagen from './Imagen';
import Lista from './Lista';

export default function Autor ({person}) {
    return (
        <>
            <h2> {person.nombre} </h2>
            <Imagen person={{nombre: person.nombre, foto: person.foto, nacimiento: person.nacimiento, educacion: person.educacion, 
                carrera: person.carrera, contribuciones: person.contribuciones, premios: person.premios, bibliografia: person.bibliografia}}/>
            <Lista person={{nombre: person.nombre, foto: person.foto, nacimiento: person.nacimiento, educacion: person.educacion, 
                carrera: person.carrera, contribuciones: person.contribuciones, premios: person.premios, bibliografia: person.bibliografia}}/>
        </>
    );
}
function Imagen ({person}) {
    return (
        <img
            className="avatar"
            src={person.foto}
            alt={'Foto de ' + person.nombre}
            style={{
              width: 120,
              height: 120,
              paddingLeft: 20
            }}
        />
    );
}

function Lista ({person}) {
    var educacion = person.educacion.map(edu => <li className="item"> {edu} </li>);
    var carrera = person.carrera.map(car => <li className="item"> {car} </li>);
    var contribuciones = person.contribuciones.map(cont => <li className="item"> {cont} </li>);
    var premios = person.premios.map(prem => <li className="item"> {prem} </li>);
    var bibliografia = person.bibliografia.map(bib => <li className="item"> {bib} </li>);
    return (
        <>
        <ul> <strong>Nacimiento:</strong> <li className="item">{person.nacimiento}</li> </ul>
        <ul> <strong>Educacion:</strong> {educacion} </ul>
        <ul> <strong>Carrera:</strong> {carrera} </ul>
        <ul> <strong>Contribuciones:</strong> {contribuciones} </ul>
        <ul> <strong>Premios:</strong> {premios} </ul>
        <ul> <strong>Bibliografia:</strong> {bibliografia} </ul>
        </>
    );
}

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
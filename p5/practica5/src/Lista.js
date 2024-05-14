export default function Lista ({person}) {
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
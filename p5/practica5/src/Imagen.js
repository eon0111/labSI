export default function Imagen ({person}) {
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
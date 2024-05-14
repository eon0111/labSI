export default function Glosario ({link}) {
    return (
        <>
        <a href={link.enlace} style={{color: "#0000AA", textDecoration: "underline"}}>{link.nombre}</a>
        <br/>
        </>
    );
}
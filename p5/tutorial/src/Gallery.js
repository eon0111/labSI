export function Profile() {
    return (
      <img src="https://i.imgur.com/MK3eW3Am.jpg" alt="Katherine Johnson"/>
    )
  }
  
  export default function Gallery (props) {
    return (
      <section>
        <h1>Amazing scientists</h1>
        <Profile/>
        <Profile/>
        <Profile/>
      </section>
    )
  }
  
  /*const user = {
    name: 'Hedy Lamarr',
    imageUrl: 'https://i.imgur.com/yXOvdOSs.jpg',
    imageSize: 90,
  };
  
  export default function Profile() {
    return (
      <>
        <h1>{user.name}</h1>
        <img
          className="avatar"
          src={user.imageUrl}
          alt={'Photo of ' + user.name}
          style={{
            width: user.imageSize,
            height: user.imageSize
          }}
        />
      </>
    );
  }*/
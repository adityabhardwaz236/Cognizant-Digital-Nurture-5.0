import React from "react";

class Posts extends React.Component{
    constructor(props){
        super(props);

        this.state = {posts:[]};
    }
    loadPosts(){
        fetch("https://jsonplaceholder.typicode.com/posts")
            .then(response=>response.json())
            .then(data=>{
                
                this.setState({
                    posts:data
                });
            })
            .catch(error=>console.log(error));
    }

    componentDidMount(){
        this.loadPosts();
    }

    render(){
        return(
            <div>
                <h1><b>Posts</b></h1>
                {
                this.state.posts.map(post=>(
                    <div key={post.id}>
                        <h3>{post.title}</h3>
                        <p>{post.body}</p>
                    </div>
                ))
            }
            </div>
        );
    }
    
    componentDidCatch(error, info){
        console.log(error);
        console.log(info);
    }
}

export default Posts
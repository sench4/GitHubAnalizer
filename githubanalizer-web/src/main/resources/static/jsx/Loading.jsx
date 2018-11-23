class Loading extends React.Component{
    constructor(props){
        super(props);
    }

    render(){
        return (<div id={this.props.id} className={this.props.className ? this.props.className + " loading" : "loading"} style={{display: this.props.display}}><div>Loading…</div></div>);
    }
}
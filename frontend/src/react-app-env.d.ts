/// <reference types="react-scripts" />

declare module "react-stomp" {
  declare interface StompClient {}

  declare interface ReactStompRef {}

  declare interface ReactStompProps {
    ref?: (client: StompClient) => client;
    url: string;
    options?: any;
    topics: string[];
    onConnect?: () => void;
    onDisconnect?: () => void;
    getRetryInterval?: () => number;
    onMessage: <T extends unknown>(message: T) => void;
    subscribeHeaders?: { [header as string]: string };
    autoReconnect?: boolean;
    debug?: boolean;
    heartbeat?: number;
    heartbeatIncoming?: number;
    heartbeatOutgoing?: number;
  }

  declare const ReactStomp: React.FC<ReactStompProps> = (props) => {};

  export default ReactStomp;
}

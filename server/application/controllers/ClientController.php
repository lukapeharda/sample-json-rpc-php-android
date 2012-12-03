<?php

class ClientController extends Zend_Controller_Action
{
    public function indexAction()
    {
        $client = new Zend_XmlRpc_Client('http://localhost/xmlrpc-test/public/server');
        try {
            //$data = $client->call('cf.test');        	
            $data = $client->call('cf.getData', 200);
            $this->view->data = $data;
            $httpClient = $client->getHttpClient();
            $response = $httpClient->getLastResponse();
            echo(strlen($response->getRawBody())/1024);
        } catch (Zend_XmlRpc_Client_HttpException $e) {
            require_once 'Zend/Exception.php';
            throw new Zend_Exception($e);
        } catch (Zend_XmlRpc_Client_FaultException $e) {
         	require_once 'Zend/Exception.php';
            throw new Zend_Exception($e);
        }
    }

    public function jsonAction()
    {
        $this->view->serverUrl = 'http://localhost/xmlrpc-test/public/server/json';
        $this->view->dataNumber = 200;
    }


}


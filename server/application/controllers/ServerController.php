<?php

class ServerController extends Zend_Controller_Action
{
    public function indexAction()
    {
    	$this->_helper->viewRenderer->setNoRender();
    	
        $server = new Zend_XmlRpc_Server();
		$server->setClass('Application_Model_Data', 'cf');
		
		echo $server->handle();
    }
    
    public function jsonAction()
    {
    	$this->_helper->viewRenderer->setNoRender();
    	
    	$server = new Zend_Json_Server();
		$server->setClass('Application_Model_Data', 'cf');
		
	    if ('GET' == $_SERVER['REQUEST_METHOD']) {
		    $server
		    	->setTarget('http://localhost/xmlrpc-test/public/server/json')
		        ->setEnvelope(Zend_Json_Server_Smd::ENV_JSONRPC_2);
		    $smd = $server->getServiceMap();

		    header('Content-Type: application/json');
		    echo $smd;
		    return;
		}
		
		echo $server->handle();
    }
}


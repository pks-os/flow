/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.dom;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import com.vaadin.flow.StateNode;
import com.vaadin.flow.event.PropertyChangeListener;
import com.vaadin.flow.nodefeature.ComponentMapping;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Component;

/**
 * Handles storing and retrieval of the state information for an element using a
 * state node.
 *
 * @author Vaadin Ltd
 */
public interface ElementStateProvider extends Serializable {

    /**
     * Checks if the element state provider supports the given state node.
     *
     * @param node
     *            the state node to check
     * @return true if the element state provider is compatible with the given
     *         state node, false otherwise
     */
    boolean supports(StateNode node);

    /**
     * Gets the tag name for the given node.
     *
     * @param node
     *            the node containing the data
     * @return the tag name
     */
    String getTag(StateNode node);

    /**
     * Sets the given attribute to the given value.
     *
     * @param node
     *            the node containing the data
     * @param attribute
     *            the attribute name, not null
     * @param value
     *            the attribute value
     */
    void setAttribute(StateNode node, String attribute, String value);

    /**
     * Sets the given attribute to the given {@link StreamResource} value.
     *
     * @param node
     *            the node containing the data
     * @param attribute
     *            the attribute name, not null
     * @param resource
     *            the attribute value, not null
     */
    void setAttribute(StateNode node, String attribute,
            StreamResource resource);

    /**
     * Gets the value of the given attribute.
     *
     * @param node
     *            the node containing the data
     * @param attribute
     *            the attribute name, not null
     * @return the attribute value or null if the attribute has not been set
     */
    String getAttribute(StateNode node, String attribute);

    /**
     * Checks if the given attribute has been set.
     *
     * @param node
     *            the node containing the data
     * @param attribute
     *            the attribute name, not null
     * @return true if the attribute has been set, false otherwise
     */
    boolean hasAttribute(StateNode node, String attribute);

    /**
     * Removes the given attribute if it has been set.
     *
     * @param node
     *            the node containing the data
     * @param attribute
     *            the attribute name, not null
     */
    void removeAttribute(StateNode node, String attribute);

    /**
     * Gets the defined attribute names.
     *
     * @param node
     *            the node containing the data
     * @return the defined attribute names
     */
    Stream<String> getAttributeNames(StateNode node);

    /**
     * Gets the parent element.
     *
     * @param node
     *            the node containing the data
     * @return the parent element or null if the element has no parent
     */
    @SuppressWarnings("rawtypes")
    Node getParent(StateNode node);

    /**
     * Gets the number of child elements.
     *
     * @param node
     *            the node containing the data
     * @return the number of child elements
     */
    int getChildCount(StateNode node);

    /**
     * Returns the child element at the given position.
     *
     * @param node
     *            the node containing the data
     * @param index
     *            the index of the child element to return
     * @return the child element
     */
    Element getChild(StateNode node, int index);

    /**
     * Inserts the given child at the given position.
     *
     * @param node
     *            the node containing the data
     * @param index
     *            the position at which to insert the new child
     * @param child
     *            the child element to insert
     */
    void insertChild(StateNode node, int index, Element child);

    /**
     * Removes the child at the given position.
     *
     * @param node
     *            the node containing the data
     * @param index
     *            the position of the child element to remove
     */
    void removeChild(StateNode node, int index);

    /**
     * Removes the given child.
     *
     * @param node
     *            the node containing the data
     * @param child
     *            the child element to remove
     */
    void removeChild(StateNode node, Element child);

    /**
     * Removes all child elements.
     *
     * @param node
     *            the node containing the data
     */
    void removeAllChildren(StateNode node);

    /**
     * Adds a DOM event listener.
     *
     * @param node
     *            the node containing the data
     * @param eventType
     *            the event type
     * @param listener
     *            the listener
     * @param eventDataExpressions
     *            the event data expressions
     *
     * @return a handle for removing the listener
     */
    EventRegistrationHandle addEventListener(StateNode node, String eventType,
            DomEventListener listener, String[] eventDataExpressions);

    /**
     * Gets the value of the given property.
     *
     * @param node
     *            the node containing the data
     * @param name
     *            the property name, not null
     * @return the property value, or <code>null</code> if the property has not
     *         been set
     */
    Serializable getProperty(StateNode node, String name);

    /**
     * Sets the given property to the given value.
     *
     * @param node
     *            the node containing the data
     * @param name
     *            the property name, not <code>null</code>
     * @param value
     *            the property value
     * @param emitChange
     *            true to create a change event for the client side
     */
    void setProperty(StateNode node, String name, Serializable value,
            boolean emitChange);

    /**
     * Removes the given property if it has been set.
     *
     * @param node
     *            the node containing the data
     * @param name
     *            the property name, not <code>null</code>
     */
    void removeProperty(StateNode node, String name);

    /**
     * Checks if the given property has been set.
     *
     * @param node
     *            the node containing the data
     * @param name
     *            the property name, not <code>null</code>
     *
     * @return <code>true</code> if the property has been set,
     *         <code>false</code> otherwise
     */
    boolean hasProperty(StateNode node, String name);

    /**
     * Gets the defined property names.
     *
     * @param node
     *            the node containing the data
     * @return the defined property names
     */
    Stream<String> getPropertyNames(StateNode node);

    /**
     * Checks if the state node represents a text node.
     *
     * @param node
     *            the node to check
     * @return <code>true</code> if the state node represents a text node;
     *         otherwise <code>false</code>
     */
    boolean isTextNode(StateNode node);

    /**
     * Gets the text content. This is only valid if
     * {@link #isTextNode(StateNode)} returns <code>true</code>.
     *
     * @param node
     *            the node containing the data
     * @return the text content
     */
    String getTextContent(StateNode node);

    /**
     * Sets the text content. This is only valid if
     * {@link #isTextNode(StateNode)} returns <code>true</code>.
     *
     * @param node
     *            the node containing the data
     * @param textContent
     *            the text content, not null
     */
    void setTextContent(StateNode node, String textContent);

    /**
     * Gets a list representation of all CSS class names set for an element.
     *
     * @param node
     *            the node containing the data
     * @return the class list
     */
    ClassList getClassList(StateNode node);

    /**
     * Returns a style instance for managing element inline styles.
     *
     * @param node
     *            the node containing the data
     * @return the element styles
     */
    Style getStyle(StateNode node);

    /**
     * Gets the names of the properties to synchronize from the client side to
     * the server.
     * <p>
     * The events which trigger synchronization are defined using
     * {@link #getSynchronizedPropertyEvents(StateNode)}.
     *
     * @param node
     *            the node containing the data
     * @return the names of the properties to synchronize
     */
    Set<String> getSynchronizedProperties(StateNode node);

    /**
     * Gets the event types which should trigger synchronization of properties
     * from the client side to the server.
     *
     * @param node
     *            the node containing the data
     * @return the event types which should trigger synchronization
     */
    Set<String> getSynchronizedPropertyEvents(StateNode node);

    /**
     * Defines a mapping between the element and the given component.
     *
     * @param node
     *            the node containing the data
     * @param component
     *            the component to map the element to
     */
    default void setComponent(StateNode node, Component component) {
        assert node != null;
        assert component != null;
        node.getFeature(ComponentMapping.class).setComponent(component);
    }

    /**
     * Gets the component this element is mapped to.
     *
     * @param node
     *            the node containing the data
     * @return an optional component, or an empty optional if no component has
     *         been mapped to this node
     */
    default Optional<Component> getComponent(StateNode node) {
        assert node != null;
        return node.getFeature(ComponentMapping.class).getComponent();
    }

    /**
     * Adds a property change listener.
     * 
     * @param node
     *            the node containing the property
     * @param name
     *            the property name to add the listener for
     * @param listener
     *            listener to get notifications about property value changes
     * @return an event registration handle for removing the listener
     */
    EventRegistrationHandle addPropertyChangeListener(StateNode node,
            String name, PropertyChangeListener listener);

    /**
     * Gets shadow root for the {@code node} if it has been attached.
     * 
     * @param node
     *            the node having a shadow root, not {@code null}
     * @return the shadow root of the {@code node}, may be null
     */
    StateNode getShadowRoot(StateNode node);

    /**
     * Attaches the shadow root for the {@code node}.
     * 
     * @param node
     *            the node to attach the shadow root
     * @return the shadow root of the {@code node}
     */
    StateNode attachShadow(StateNode node);

    /**
     * Attaches a child element with the given {@code tagName} which is the next
     * sibling for the {@code previousSibling}.
     * <p>
     * The {@code previousSibling} parameter value can be {@code null} which
     * means that the very first child with the given {@code tagName} will be
     * used to attach (if any).
     * 
     * @param node
     *            the parent node
     * @param tagName
     *            the tag name of the element to attach, not {@code null}
     * @param previousSibling
     *            previous sibling, may be {@code null}
     * @param callback
     *            the callback which will be invoked with a server side element
     *            instance or an error will be reported, not {@code null}
     */
    void attachExistingElement(StateNode node, String tagName,
            Element previousSibling, ChildElementConsumer callback);

}
